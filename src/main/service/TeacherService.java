package main.service;

import main.vo.Classes;
import main.vo.Course;
import main.vo.Score;
import main.vo.Student;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/20
 */
public interface TeacherService {
    Boolean insertScore(Score score);


    List<Course> queryCourseByTeacherId(int teacherId);//根据教师的工号查询他带的课程

    List<Score> queryScoreByTeacherId(int teacherId);//根据教师Id查询到他的所有学生的分数（成绩还没输入，只是一个表单）

    List<Score> queryScoreByTeacherIdFromScore(int teacherId);//根据教师Id查询到他的所有学生的成绩

    List<Score> queryScoreByTeacherIdAndCourseId(int teacherId, int courseId);//根据课程及教师Id,课程id获取该教师某门课程学生成绩

    List<Score> getAllScoreByTeacherIdAndCourseIdFromScore(int teacherId, int courseId);//到Score表中查询到该教师所有学生成绩（指定课程）


    boolean updateScoreByStudentIdAndCourseId(int studentId, int courseId, int grade);
}
