package main.dao;

import main.vo.Course;
import main.vo.Score;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/19
 */
public interface ScoreDao {

    void updateScore(Score score);

    List<Score> selectScoreByStudentId(int studentId);

    List<Score> selectScoreByCourseName(String courseName);

    boolean insertScore(Score score);

    Integer getAllRowsAmount() throws Exception;

    List<Score> getScoreByCurrentPage(Integer currentPageStartRow, Integer pageSize) throws Exception;

    /**
     * 根据score提供的属性删除
     *
     * @param score
     */
    boolean deleteScore(Score score);

    /**
     * 修改学号为studentId，课程号为courseId的成绩
     * @param studentId
     * @param courseId
     * @param grade
     */
    boolean updateScoreByStudentIdAndCourseId(int studentId, int courseId, int grade);

    List<Score> queryScoreByTeacherId(int teacherId);//根据教师Id查询他的所有学生的分数

    /**
     * 通过教师Id和课程查询自己学生该门课的成绩
     * @param teacherId
     * @param courseId
     * @return
     */
    List<Score> queryScoreByTeacherIdAndCourseId(int teacherId, int courseId);

    /**
     * 通过学生Id和课程查询成绩
     * @param studentId
     * @param courseId
     * @return
     */
    Score queryScoreByStudentIdAndCourseId(int studentId, int courseId);

    /**
     * 得到该教师的所有学生的成绩
     * @param teacherId
     * @return
     */
    List<Score> getAllScoreByTeacherId(int teacherId);

}
