package main.controller.adminOperation;

import main.dao.ClassesDao;
import main.dao.daoImpl.ClassesDaoImpl;
import main.service.AdminService;
import main.service.TeacherService;
import main.service.serviceImpl.AdminServiceImpl;
import main.service.serviceImpl.TeacherServiceImpl;
import main.vo.Classes;
import main.vo.Score;
import main.vo.Student;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.ReadExcel;
import util.ReadStudentsExcel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/16
 */
@WebServlet("/UploadStudents")
public class UploadStudents extends HttpServlet {
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

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        System.out.println(filePath);
                        item.write(storeFile);
                        req.setAttribute("message", "文件上传成功");

                        //上传数据库
                        File studentFile = new File(getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY +"/"+fileName);
                        ReadStudentsExcel readStudentsExcel = new ReadStudentsExcel();

                        List<Student> studentList= readStudentsExcel.readStudentExcel(storeFile);
                        System.out.println("上传的学生表：" + studentList);
                        AdminService adminService = new AdminServiceImpl();
                        ClassesDao classesDao = new ClassesDaoImpl();
                        for (Student student :
                                studentList) {
                            adminService.addStudent(student);
                            Classes classes = new Classes();
                            classes = classesDao.selectClassByClassId(student.getClassId());
                            classesDao.addStudentToClass(student, classes);
                        }
                    }

                }
            }

        } catch (Exception e) {
            req.setAttribute("message", "错误信息：" + e.getMessage());
        }


        getServletContext().getRequestDispatcher("/selectStudent").forward(req, resp);
    }
}
