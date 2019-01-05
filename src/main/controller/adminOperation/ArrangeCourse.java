package main.controller.adminOperation;

import main.dao.ClassesDao;
import main.dao.CourseDao;
import main.dao.TeacherCourseDao;
import main.dao.TeacherDao;
import main.dao.daoImpl.ClassesDaoImpl;
import main.dao.daoImpl.CourseDaoImpl;
import main.dao.daoImpl.TeacherCourseDaoImpl;
import main.dao.daoImpl.TeacherDaoImpl;
import main.service.AdminService;
import main.service.serviceImpl.AdminServiceImpl;
import main.vo.Classes;
import main.vo.Course;
import main.vo.CourseArranged;
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
 * Create by pengweijie on 2018/12/5
 */
@WebServlet("/arrangeCourse")
public class ArrangeCourse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AdminService adminService = new AdminServiceImpl();
        TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
        ClassesDao classesDao = new ClassesDaoImpl();
        CourseDao courseDao = new CourseDaoImpl();
        TeacherDao teacherDao = new TeacherDaoImpl();

        if ((req.getParameter("classesId") != null) && (req.getParameter("courseId") != null) && (req.getParameter("teacherId") != null)) {

            int classId = Integer.parseInt(req.getParameter("classesId"));
            int courseId = Integer.parseInt(req.getParameter("courseId"));
            int teacherId = Integer.parseInt(req.getParameter("teacherId"));


            Classes classes = classesDao.selectClassByClassId(classId);
            Course course = courseDao.selectCourseByCourseId(courseId);
            Teacher teacher = teacherDao.findTeacherById(teacherId);

            if (adminService.arrangeCourse(classes, course, teacher)) {

                adminService.updateInfoTo_teacherCourse(course, teacher);
                adminService.updateInfoTo_teacherClass(classes,teacher);

                List<CourseArranged> courseArrangedList = teacherCourseDao.getAllArrangedCourse();
                HttpSession session = req.getSession();
                session.setAttribute("courseArrangedList", courseArrangedList);

                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("1");
            }
        } else {
            List<Classes> classesList = new ArrayList<Classes>();
            List<Course> courseList = new ArrayList<Course>();
            List<Teacher> teacherList = new ArrayList<Teacher>();

            classesList = adminService.getAllClasses();
            courseList = adminService.getAllCourse();
            teacherList = adminService.getAllTeacher();

            req.setAttribute("classesList", classesList);
            req.setAttribute("courseList", courseList);
            req.setAttribute("teacherList", teacherList);
            System.out.println(classesList);
            System.out.println(courseList);
            System.out.println(teacherList);
            //req.getRequestDispatcher(req.getContextPath() + "jsp/admin/arrangeCourse.jsp").forward(req, resp);

            List<CourseArranged> courseArrangedList = teacherCourseDao.getAllArrangedCourse();
            HttpSession session = req.getSession();
            session.setAttribute("courseArrangedList", courseArrangedList);
            req.getRequestDispatcher(req.getContextPath() + "jsp/admin/arrangeCourse.jsp").forward(req, resp);
        }


    }
}
