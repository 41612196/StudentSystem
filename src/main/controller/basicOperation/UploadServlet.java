package main.controller.basicOperation;

import com.sun.deploy.util.SyncAccess;
import main.service.TeacherService;
import main.service.serviceImpl.TeacherServiceImpl;
import main.vo.Score;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import util.CloseDataAccess;
import util.DataAccess;
import util.POIUtil;
import util.ReadExcel;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/16
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIRECTORY = "../../../web/images/upload";

    private static final int MEMORY_THRESHOLD = 1024 * 1021 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(req)) {
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        HttpSession session = req.getSession();
        String superuser = (String) session.getAttribute("superuser");
        int userId = Integer.parseInt((String) session.getAttribute("username"));

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        System.out.println("上传文件的名称：" + fileName);


                        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                        System.out.println("文件名后缀" + fileSuffix);
                        if (fileSuffix.equals("jpg") || fileSuffix.equals("png") || fileSuffix.equals("jpeg")) {//如果是图片则上传到该人的信息表
                            String photoPath = null;
                            String path = null;//相对路径
                            if (superuser.equals("1")) {
                                photoPath = uploadPath + File.separator + "pic/student/" + fileName;
                                path = "/images/upload/pic/student/" + fileName;
                            } else if (superuser.equals("2")) {
                                photoPath = uploadPath + File.separator + "pic/teacher/" + fileName;
                                path = "/images/upload/pic/teacher/" + fileName;
                            }

                            File storeFile = new File(photoPath);
                            System.out.println("文件路径" + photoPath);
                            item.write(storeFile);
                            req.setAttribute("message", "文件上传成功");
                            System.out.println("文件上传OK");
                            System.out.println("是图片");


                            Connection connection = null;
                            Statement statement = null;
                            ResultSet resultSet = null;
                            PreparedStatement preparedStatement = null;
                            FileInputStream in = null;
                            FileOutputStream out = null;
                            InputStream is = null;

                            System.out.println(userId);
                            System.out.println(superuser);
                            System.out.println("用户名：" + userId + "权限：" + superuser);
                            String sql = null;
                            switch (superuser) {
                                case "1":
                                    sql = "UPDATE student SET photo=? WHERE studentId=?";
                                    break;
                                case "2":
                                    sql = "UPDATE teacher SET photo=? WHERE teacherId=?";
                                    break;
                            }
                            try {
                                connection = DataAccess.getConnection();
                                statement = connection.createStatement();
                                preparedStatement = connection.prepareStatement(sql);
                                //1.存图片的相对路径
                                preparedStatement.setString(1, path);
                                preparedStatement.setInt(2, userId);
                                preparedStatement.executeUpdate();

                                if (superuser.equals("1")) {
                                    req.getRequestDispatcher(req.getContextPath() + "/studentInfo").forward(req, resp);


                                   // getServletContext().getRequestDispatcher("/studentInfo").forward(req, resp);
                                    return;

                                } else if (superuser.equals("2")) {
                                    getServletContext().getRequestDispatcher("/teacherInfo").forward(req, resp);
                                    return;

                                }

                                //2.以下是存图片二进制流
//                                File photoFile = new File(path);
//                                in = new FileInputStream(photoFile);
//                                preparedStatement.setBinaryStream(1, in, photoFile.length());
//                                preparedStatement.setInt(2, userId);
//                                preparedStatement.executeUpdate();
                                //以上是传图片到数据库
                                //以下是从数据库下载图片
//                                photoFile = new File("d:\\new.jpg");
//                                out = new FileOutputStream(photoFile);
//                                statement = connection.createStatement();
//                                preparedStatement = connection.prepareStatement("select photo FROM teacher WHERE teacherId=?");
//                                preparedStatement.setInt(1, userId);
//                                resultSet = preparedStatement.executeQuery();
//
//                                resultSet.next();
//                                Blob blob = resultSet.getBlob("photo");
//                                is = blob.getBinaryStream();
//                                int i=0;
//                                while ((i=is.read())!=-1)
//                                    out.write(i);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } finally {
                                CloseDataAccess.closeDataAccess2(preparedStatement, connection);
                            }
                        } else {
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            System.out.println("文件路径" + filePath);
                            item.write(storeFile);
                            req.setAttribute("message", "文件上传成功");
                            System.out.println("文件上传OK");


                            File scoreFile = new File(getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY + "/" + fileName);
                            ReadExcel readExcel = new ReadExcel();
                            List<Score> scoreList = readExcel.readExcel(scoreFile);
                            TeacherService teacherService = new TeacherServiceImpl();
                            for (Score score :
                                    scoreList) {
                                teacherService.insertScore(score);
                            }
                        }

                        getServletContext().getRequestDispatcher("/teacherSelectScore").forward(req, resp);
                        //上传数据库

                    }

                }
            }

        } catch (Exception e) {
            System.out.println("错啦");

            req.setAttribute("message", "错误信息：" + e.getMessage());
            e.printStackTrace();
        }


    }
}
