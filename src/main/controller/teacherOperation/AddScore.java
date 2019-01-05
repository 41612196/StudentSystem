package main.controller.teacherOperation;

import main.dao.CourseDao;
import main.dao.TeacherCourseDao;
import main.dao.daoImpl.CourseDaoImpl;
import main.dao.daoImpl.TeacherCourseDaoImpl;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Create by pengweijie on 2018/11/26
 */
@WebServlet("/addScore")
public class AddScore extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();
    private TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
    private TeacherService teacherService = new TeacherServiceImpl();
    private CourseDao courseDao = new CourseDaoImpl();
    List<Score> scoreList = new ArrayList<Score>();

    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        System.out.println(username);

        int teacherId = Integer.parseInt(username);
        List<Course> courseList = teacherCourseDao.queryCourseByTeacherId(teacherId);//教师的课程
        System.out.println("该教师的课程有：" + courseList);

        String selectCourse;
        selectCourse=request.getParameter("selectCourse");
        System.out.println("45454654654654选的课：" + selectCourse);


        boolean sc=true;
        Course course = new Course();
        if (selectCourse==null) {
            sc = true;
        } else {

            System.out.println("选的课是："+selectCourse);
            course = courseDao.selectCourseByCourseName(selectCourse);
            System.out.println("选的课的全部信息：" + course);
            if (course == null) {//选的课程不存在
                sc = true;
                System.out.println("选的课程不存在");
            }else{
                for (Course course1:
                        courseList) {
                    if (course.getCourseId()==course1.getCourseId()) {//判断输入的课程在不在该教师的课程中，如果在返回选了课程，否则，相当于没选
                        session.setAttribute("selectCourse", course);
                        System.out.println("这门课在教师的课程中："+course);
                        sc = false;
                    }

                }
            }


        }

        if (sc) {
            scoreList = teacherService.queryScoreByTeacherId(teacherId);
            System.out.println("未选择课程");
        } else {
            scoreList = null;
            scoreList = teacherService.queryScoreByTeacherIdAndCourseId(teacherId, course.getCourseId());//
            System.out.println("选择了课程");
        }

        System.out.println(scoreList);
        session.setAttribute("scoreList", scoreList);
        request.getRequestDispatcher(request.getContextPath() + "jsp/teacher/studentScoreAddList.jsp").forward(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
