package main.controller.teacherOperation;

import main.dao.CourseDao;
import main.dao.daoImpl.CourseDaoImpl;
import main.service.StudentService;
import main.service.TeacherService;
import main.service.serviceImpl.StudentServiceImpl;
import main.service.serviceImpl.TeacherServiceImpl;
import main.vo.Course;
import main.vo.Score;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Create by pengweijie on 2018/11/30
 */
@WebServlet("/teacherSelectScore")
public class TeacherSelectScore extends HttpServlet {


    private TeacherService teacherService = new TeacherServiceImpl();
    private CourseDao courseDao = new CourseDaoImpl();

    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Score> scoreList = null;
        String username = (String) session.getAttribute("username");
        System.out.println(username);

        String selectCourse;
        selectCourse=request.getParameter("selectCourse");


        boolean sc;
        Course course = new Course();
        if (selectCourse == null) {
            sc = true;
        } else {
            sc = false;
            selectCourse = new String((request.getParameter("selectCourse")).getBytes("ISO-8859-1"), "UTF-8");
        }

        int teacherId = Integer.parseInt(username);
        if (selectCourse==null) {
            scoreList = teacherService.queryScoreByTeacherIdFromScore(teacherId);
            System.out.println("没分课程：" + scoreList);
        } else {
            course = courseDao.selectCourseByCourseName(selectCourse);
            //scoreList = teacherService.queryScoreByTeacherIdAndCourseId(teacherId, course.getCourseId());
            System.out.println("分了课程：" + scoreList);
        }

        System.out.println("该教师学生的成绩："+scoreList);
        session.setAttribute("scoreList", scoreList);
        request.removeAttribute("selectCourse");

        request.getRequestDispatcher(request.getContextPath() + "jsp/teacher/studentScoreList_teacher.jsp").forward(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}


