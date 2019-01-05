package main.dao;

import main.vo.Score;
import main.vo.Student;
import main.vo.Teacher;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/19
 */
public interface TeacherDao {
    boolean deleteById(int teacherId);

    boolean insertTeacher(Teacher teacher);

    Teacher findTeacherById(int teacherId);

    void updateTeacher(Teacher teacher);

    List<Teacher> getAllTeacher();
    List<Integer> getAllStudentIdByTeacherId(int teacherId);//得到该教师的所有学生


    List<Score> getAllScoreByTeacherIdAndCourseIdFromScore(int teacherId, int courseId);//到Score表中查询到该教师所有学生成绩（指定课程）
}
