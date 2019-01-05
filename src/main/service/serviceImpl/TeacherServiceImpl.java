package main.service.serviceImpl;

import main.DaoFactory.DaoFactory;
import main.dao.CourseDao;
import main.dao.ScoreDao;
import main.dao.TeacherCourseDao;
import main.dao.TeacherDao;
import main.dao.daoImpl.ScoreDaoImpl;
import main.dao.daoImpl.TeacherCourseDaoImpl;
import main.dao.daoImpl.TeacherDaoImpl;
import main.service.TeacherService;
import main.vo.Classes;
import main.vo.Course;
import main.vo.Score;
import main.vo.TeacherCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/11/20
 */
public class TeacherServiceImpl implements TeacherService {
    TeacherDao teacherDao = new TeacherDaoImpl();
    TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();
    ScoreDao scoreDao = new ScoreDaoImpl();


    @Override
    public List<Score> queryScoreByTeacherId(int teacherId) {//根据教师Id获取他的所有学生的分数单（还没成绩）
        List<Score> scoreList = scoreDao.queryScoreByTeacherId(teacherId);
        return scoreList;
    }

    @Override
    public List<Score> queryScoreByTeacherIdFromScore(int teacherId) {//根据教师Id获取他的所有学生的分数单（有成绩）
        List<Score> scoreList = new ArrayList<Score>();
        ScoreDao scoreDao = new ScoreDaoImpl();
        scoreList = scoreDao.getAllScoreByTeacherId(teacherId);
        return scoreList;

    }


    @Override
    public List<Course> queryCourseByTeacherId(int teacherId) {//根据教师Id获取他的课程
        List<Course> courseList = teacherCourseDao.queryCourseByTeacherId(teacherId);
        return courseList;
    }

    @Override
    public Boolean insertScore(Score score) {
        ScoreDao scoreDao = new ScoreDaoImpl();
        return scoreDao.insertScore(score);
    }

    @Override
    public boolean updateScoreByStudentIdAndCourseId(int studentId, int courseId, int grade) {
        return scoreDao.updateScoreByStudentIdAndCourseId(studentId, courseId, grade);
    }

    @Override
    /*
    //到Score表中查询到该教师所有学生成绩（指定课程）
     */
    public List<Score> getAllScoreByTeacherIdAndCourseIdFromScore(int teacherId, int courseId) {
        return teacherDao.getAllScoreByTeacherIdAndCourseIdFromScore(teacherId, courseId);
    }

    @Override
    public List<Score> queryScoreByTeacherIdAndCourseId(int teacherId, int courseId) {
        List<Score> scoreList = scoreDao.queryScoreByTeacherIdAndCourseId(teacherId, courseId);
        return scoreList;
    }
}
