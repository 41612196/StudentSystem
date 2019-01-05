package main.controller.teacherOperation;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.dao.CourseDao;
import main.dao.daoImpl.CourseDaoImpl;
import main.service.StudentService;
import main.service.TeacherService;
import main.service.serviceImpl.TeacherServiceImpl;
import main.vo.Score;
import main.vo.ScorePeople;

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
 * Create by pengweijie on 2018/12/1
 */
@WebServlet("/analyzeScores")
public class AnalyzeScores extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("88888888888888888888888888888");
        List<ScorePeople> list = new ArrayList<ScorePeople>();

        List<Score> scoreList = null;

        CourseDao courseDao = new CourseDaoImpl();

        TeacherService teacherService = new TeacherServiceImpl();
        HttpSession session = req.getSession();
        int teacherId = Integer.parseInt((String) session.getAttribute("username"));//获取教师的Id
        String courseName = null;
        courseName = (String)session.getAttribute("courseName");//选择的课程（统计该课程的各分数段人数）
        session.removeAttribute("courseName");

        System.out.println("统计课程：" + courseName);

        int num1 = 0;       //<60分数段的人数
        int num2 = 0;       //60-69分数段的人数
        int num3 = 0;       //70-89分数段的人数
        int num4 = 0;       //90-100分数段的人数
        float passrate;//及格率


        if (courseName != null) {
            int courseId = (courseDao.selectCourseByCourseName(courseName)).getCourseId();//得到课程的Id
            scoreList = teacherService.getAllScoreByTeacherIdAndCourseIdFromScore(teacherId, courseId);
            for (Score score :
                    scoreList) {
                if (score.getGrade() < 60) {
                    num1++;
                    System.out.println("<60"+num1);

                } else if (score.getGrade() < 70 && score.getGrade() >= 60) {
                    num2++;
                    System.out.println("<70"+num2);

                } else if (score.getGrade() < 90 && score.getGrade() >= 70) {
                    num3++;
                    System.out.println("<90"+num3);

                } else if (score.getGrade() <= 100 && score.getGrade() >= 90) {
                    num4++;
                    System.out.println("<100"+num4);

                }

            }
        }
        passrate=(float) (num2+num3+num4)/(num1+num2+num3+num4);
        System.out.println("及格率为" + passrate);

        list.add(new ScorePeople("不及格", num1, passrate));
        list.add(new ScorePeople("60-69", num2));
        list.add(new ScorePeople("70-89", num3));
        list.add(new ScorePeople("90-100", num4));
        ObjectMapper mapper = new ObjectMapper();//提供java-json相互装换功能

        String json = mapper.writeValueAsString(list);//将list中的对象转换为Json格式的

        System.out.println(json);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(json);

    }
}
