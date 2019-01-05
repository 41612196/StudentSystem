package main.dao.daoImpl;

import main.dao.ScoreDao;
import main.vo.Classes;
import main.vo.Course;
import main.vo.Score;
import util.CloseDataAccess;
import util.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/11/20
 */
public class ScoreDaoImpl implements ScoreDao {
    @Override
    public void updateScore(Score score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE score SET studentId=?,courseId=?,studentName=?,courseName=?,grade=?");
            preparedStatement.setInt(1, score.getStudentId());
            preparedStatement.setInt(2, score.getCourseId());
            preparedStatement.setString(3, score.getStudentName());
            preparedStatement.setString(4, score.getCourseName());
            preparedStatement.setInt(5, score.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateScoreByStudentIdAndCourseId(int studentId, int courseId, int grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE score SET grade=? WHERE studentId=? AND courseId=?");
            preparedStatement.setInt(1, grade);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseDataAccess.closeDataAccess2(preparedStatement, connection);
        }
        return true;
    }

    @Override
    /**
     * 查询学号studentId课程号courseId的分数
     */
    public Score queryScoreByStudentIdAndCourseId(int studentId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Score score = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from score WHERE studentId=? AND courseId=?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            resultSet = preparedStatement.executeQuery();
            score = new Score();
            while (resultSet.next()) {

                score.setStudentId(resultSet.getInt(1));
                score.setCourseId(resultSet.getInt(2));
                score.setStudentName(resultSet.getString(3));
                score.setCourseName(resultSet.getString(4));
                score.setGrade(resultSet.getInt(5));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return score;
    }

    @Override
    /**
     * 根据教师studentId和courseId查询上这门课学生的成绩单（未填）
     */
    public List<Score> queryScoreByTeacherIdAndCourseId(int teacherId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Score> scoreList = new ArrayList<Score>();
        Score score = null;

        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT studentId,studentName,courseId,courseName " +
                            "FROM teacher_course,teacher_class,student_class " +
                            "WHERE teacher_course.teacherId=? AND" +
                            " teacher_course.courseId=? AND teacher_course.teacherId=teacher_class.teacherId" +
                            " AND student_class.classId=teacher_class.classId");
            preparedStatement.setInt(1, teacherId);
            preparedStatement.setInt(2, courseId);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            System.out.println("该教师共有学生：" + rowCount + "选这个课");
            int studentId[] = new int[rowCount];
            String studentName[] = new String[rowCount];
            // int courseId[] = new int[rowCount];
            String courseName[] = new String[rowCount];

            int i = 0;
            while (resultSet.next()) {//从resultset中得到每一列的值

                studentId[i] = resultSet.getInt(1);
                studentName[i] = resultSet.getString(2);
                //courseId[i] = resultSet.getInt(3);
                courseName[i] = resultSet.getString(4);
                i++;
            }

            for (int j = 0; j < rowCount; j++) {//整合每一列的值，还原该教师的所有学生及其课程，并添加到待填分数列表中
                score = new Score();
                score.setStudentId(studentId[j]);
                score.setStudentName(studentName[j]);
                score.setCourseId(courseId);
                score.setCourseName(courseName[j]);

                scoreList.add(score);
            }
            System.out.println(scoreList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    /**
     * 根据教师studentId查询他所有学生的成绩单（未填）
     */
    public List<Score> queryScoreByTeacherId(int teacherId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Score> scoreList = new ArrayList<Score>();
        List<Classes> classesList = new ArrayList<Classes>();
        Score score = null;

        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT studentId,studentName,courseId,courseName " +
                            "FROM teacher_course,teacher_class,student_class " +
                            "WHERE teacher_course.teacherId=?" +
                            " AND teacher_course.teacherId=teacher_class.teacherId" +
                            " AND student_class.classId=teacher_class.classId");
            preparedStatement.setInt(1, teacherId);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            System.out.println("该教师一共有学生：" + rowCount);
            int studentId[] = new int[rowCount];
            String studentName[] = new String[rowCount];
            int courseId[] = new int[rowCount];
            String courseName[] = new String[rowCount];

            int i = 0;
            while (resultSet.next()) {//从resultset中得到每一列的值

                studentId[i] = resultSet.getInt(1);
                studentName[i] = resultSet.getString(2);
                courseId[i] = resultSet.getInt(3);
                courseName[i] = resultSet.getString(4);
                i++;
            }

            for (int j = 0; j < rowCount; j++) {//整合每一列的值，还原该教师的所有学生及其课程，并添加到待填分数列表中
                score = new Score();
                score.setStudentId(studentId[j]);
                score.setStudentName(studentName[j]);
                score.setCourseId(courseId[j]);
                score.setCourseName(courseName[j]);

                scoreList.add(score);
            }
            System.out.println(scoreList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    /**
     * 根据教师Id得到教师的所有学生的分数（有成绩）
     */
    public List<Score> getAllScoreByTeacherId(int teacherId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Score> scoreList = new ArrayList<Score>();
        List<Score> scoreList1 = queryScoreByTeacherId(teacherId);//得到该教师的学生的成绩单（还没分数的成绩单）
        Score score = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM score WHERE studentId=? AND courseId=?");
            for (Score s :
                    scoreList1) {
                preparedStatement.setInt(1, s.getStudentId());
                preparedStatement.setInt(2, s.getCourseId());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    score = new Score();
                    score.setStudentId(resultSet.getInt(1));
                    score.setCourseId(resultSet.getInt(2));
                    score.setStudentName(resultSet.getString(3));
                    score.setCourseName(resultSet.getString(4));
                    score.setGrade(resultSet.getInt(5));
                    scoreList.add(score);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return scoreList;
    }

    @Override
    /**
     * 得到studentId的所有成绩
     */
    public List<Score> selectScoreByStudentId(int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Score> scoreList = new ArrayList<Score>();
        Score score = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM score WHERE studentId=?");
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                score = new Score();
                score.setStudentId(resultSet.getInt(1));
                score.setCourseId(resultSet.getInt(2));
                score.setStudentName(resultSet.getString(3));
                score.setCourseName(resultSet.getString(4));
                score.setGrade(resultSet.getInt(5));
                scoreList.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return scoreList;
    }

    @Override
    public boolean insertScore(Score score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO score(studentId, courseId, studentName, courseName, grade)" +
                    " VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, score.getStudentId());
            preparedStatement.setInt(2, score.getCourseId());
            preparedStatement.setString(3, score.getStudentName());
            preparedStatement.setString(4, score.getCourseName());
            preparedStatement.setInt(5, score.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseDataAccess.closeDataAccess2(preparedStatement, connection);
        }
        return true;
    }

    @Override
    public Integer getAllRowsAmount() throws Exception {
        String sql = "select count(*) from score";
        Connection connection = DataAccess.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Integer allRowsAmount = 0;
        if (resultSet.next()) {
            allRowsAmount = resultSet.getInt("count(*)");
        }
        CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        return allRowsAmount;
    }

    @Override
    public List<Score> getScoreByCurrentPage(Integer currentPageStartRow, Integer pageSize) throws Exception {
        String sql = "select * from score limit " + (currentPageStartRow - 1) + "," + pageSize;
        System.out.println(sql);
        Connection connection = DataAccess.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Score> scoreList = new ArrayList<Score>();
        while (resultSet.next()) {
            Score score = new Score();
            score.setStudentId(resultSet.getInt(1));
            score.setCourseId(resultSet.getInt(2));
            score.setStudentName(resultSet.getString(3));
            score.setCourseName(resultSet.getString(4));
            score.setGrade(resultSet.getInt(5));
            scoreList.add(score);
        }
        CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        return scoreList;
    }

    @Override
    public boolean deleteScore(Score score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("DELETE from score WHERE studentId=? AND courseId=?");
            preparedStatement.setInt(1, score.getStudentId());
            preparedStatement.setInt(2, score.getCourseId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDataAccess.closeDataAccess2(preparedStatement, connection);
        }
        return true;
    }

    @Override
    public List<Score> selectScoreByCourseName(String courseName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Score> scoreList = new ArrayList<Score>();
        Score score = null;
        try {

            connection = DataAccess.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM score WHERE courseName=?");
            preparedStatement.setString(1, courseName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                score = new Score();
                score.setStudentId(resultSet.getInt(1));
                score.setCourseId(resultSet.getInt(2));
                score.setStudentName(resultSet.getString(3));
                score.setCourseName(resultSet.getString(4));
                score.setGrade(resultSet.getInt(5));
                scoreList.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseDataAccess.closeDataAccess3(resultSet, preparedStatement, connection);
        }
        return scoreList;
    }
}
