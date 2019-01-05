package main.controller.teacherOperation;

import main.DaoFactory.DaoFactory;
import main.dao.StudentDao;
import main.dao.daoImpl.StudentDaoImpl;
import main.vo.Course;
import main.vo.Score;
import main.vo.Student;
import util.DataAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Create by pengweijie on 2018/11/28
 */
@WebServlet("/saveScore")
public class SaveScore extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            int row = saveScoreTable(req, resp);
            req.setAttribute("message", row > 0 ? "成功保存" + row + "条记录!" : "保存失败!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("teacherLookScore").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        doGet(req, resp);
    }

    private int saveScoreTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,ClassNotFoundException, SQLException {
        Enumeration<String> parameterNames = request.getParameterNames();
        System.out.println("parameterNames");
        System.out.println(parameterNames);
        HttpSession session = request.getSession();
        Course selectCourse = (Course) session.getAttribute("selectCourse");

        Map<String, Integer> parameterNamesAndValues = new HashMap<String, Integer>();
        List<String> parameterNameList = new ArrayList<String>();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            parameterNamesAndValues.put(parameterName, Integer.parseInt(request.getParameter(parameterName)));

            parameterNameList.add(parameterName);
            String parameterValue = request.getParameter(parameterName);

            System.out.println(parameterName + ":" + parameterValue);
        }
        session.setAttribute("studentIdList", parameterNameList);//将该教师该门课的学生学号存到session中

        Connection connection = DataAccess.getConnection();

        PreparedStatement preparedStatement=connection.prepareStatement("insert INTO score (studentId, courseId, studentName, courseName, grade)VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE grade=?");

        Student student = new Student();
        StudentDao studentDao = new StudentDaoImpl();
        for (int i = 0; i < parameterNameList.size(); i++) {

            String key = parameterNameList.get(i);//得到学号

            try {
                student = studentDao.findStudentById(Integer.parseInt(key));
            } catch (Exception e) {
                e.printStackTrace();
            }


            int grade = parameterNamesAndValues.get(key).intValue();
            preparedStatement.setInt(1,Integer.parseInt(key));
            preparedStatement.setInt(2, selectCourse.getCourseId());
            preparedStatement.setString(3,student.getStudentName());
            preparedStatement.setString(4,selectCourse.getCourseName());
            preparedStatement.setInt(5,grade);
            preparedStatement.setInt(6, grade);


            preparedStatement.addBatch();

        }
        preparedStatement.executeBatch();
//        System.out.println("共保存了" + successCount + "条数据!");
       return 0;

    }
}
