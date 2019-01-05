package main.controller.teacherOperation;

import main.dao.CourseDao;
import main.dao.TeacherCourseDao;
import main.dao.daoImpl.CourseDaoImpl;
import main.dao.daoImpl.TeacherCourseDaoImpl;
import main.vo.Course;
import main.vo.TeacherCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Create by pengweijie on 2018/12/2
 */
@WebServlet("/addCourseToSession")
public class AddCourseToSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String courseName = req.getParameter("courseName");
        List<Course> courseList = null;

        int teacherId = Integer.parseInt((String)session.getAttribute("username"));//获取该教师的课程
        TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
        courseList = teacherCourseDao.queryCourseByTeacherId(teacherId);
        System.out.println("教师的课程：" + courseList);

        CourseDao courseDao = new CourseDaoImpl();
        Course course = null;
        course = courseDao.selectCourseByCourseName(courseName);//根据名称得到这个课程
        System.out.println(course);

        System.out.println(courseList.contains(course));
        if (course != null) {
            for (Course co :
                    courseList) {
                if (course.getCourseId() == co.getCourseId()) {
                    System.out.println("加入了session");
                    session.setAttribute("courseName", courseName);
                    break;
                }
            }
        }

//        if (courseList.contains(course)) {//如果选择的统计课程在教师的课程中，就加入session，之后进行统计，否则不加入
//            System.out.println("加入了session");
//            session.setAttribute("courseName", courseName);
//        }
        

        req.getRequestDispatcher(req.getContextPath() + "jsp/teacher/analyzeScore.jsp").forward(req, resp);

    }
}
