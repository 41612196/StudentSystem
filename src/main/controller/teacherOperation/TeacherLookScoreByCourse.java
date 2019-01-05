package main.controller.teacherOperation;

import com.sun.deploy.util.SyncAccess;
import main.dao.CourseDao;
import main.dao.TeacherCourseDao;
import main.dao.TeacherDao;
import main.dao.daoImpl.CourseDaoImpl;
import main.dao.daoImpl.TeacherCourseDaoImpl;
import main.dao.daoImpl.TeacherDaoImpl;
import main.service.TeacherService;
import main.service.serviceImpl.TeacherServiceImpl;
import main.vo.Course;
import main.vo.Score;
import main.vo.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/11/30
 * 教师按照课程分类查看成绩
 */
@WebServlet("/teacherLookScoreByCourse")
public class TeacherLookScoreByCourse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseDao courseDao = new CourseDaoImpl();
        TeacherService teacherService = new TeacherServiceImpl();
        TeacherDao teacherDao = new TeacherDaoImpl();
        TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
        String selectCourse_Look = null;
        List<Score> scoreList = new ArrayList<Score>();
        HttpSession session = req.getSession();

        if (!req.getParameter("selectCourseOne").equals("")) {
            selectCourse_Look = req.getParameter("selectCourseOne");
            System.out.println("课程分类：" + selectCourse_Look);

            String teacherId = (String) session.getAttribute("username");
            System.out.println("教师号：" + teacherId);

            List<Course> courseList = teacherCourseDao.queryCourseByTeacherId(Integer.parseInt(teacherId));
            boolean sc = true;
            Course course = new Course();
            if (selectCourse_Look == null) {
                sc = true;
            } else {

                System.out.println("选的课是：" + selectCourse_Look);
                course = courseDao.selectCourseByCourseName(selectCourse_Look);
                System.out.println("选的课的全部信息：" + course);
                if (course == null) {//选的课程不存在
                    sc = true;
                    System.out.println("选的课程不存在");
                } else {
                    for (Course course1 :
                            courseList) {
                        if (course.getCourseId() == course1.getCourseId()) {//判断输入的课程在不在该教师的课程中，如果在返回选了课程，否则，相当于没选
                            session.setAttribute("selectCourse", course);
                            System.out.println("这门课在教师的课程中：" + course);
                            sc = false;
                        }

                    }
                }


            }
            if (sc) {
                //scoreList = teacherService.queryScoreByTeacherIdFromScore(Integer.parseInt(teacherId));
                System.out.println("未选择课程");
                session.removeAttribute("scoreList");
            } else {
                scoreList = null;
                scoreList = teacherService.getAllScoreByTeacherIdAndCourseIdFromScore(Integer.parseInt(teacherId), course.getCourseId());
                System.out.println("选择了课程");
                session.setAttribute("scoreList", scoreList);
            }

            req.getRequestDispatcher(req.getContextPath() + "jsp/teacher/studentScoreList_teacher.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher(req.getContextPath() + "jsp/teacher/studentScoreList_teacher.jsp").forward(req, resp);
        }

    }
}
