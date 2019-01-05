package main.dao;

import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;
import main.DaoFactory.DaoFactory;
import main.vo.Score;
import org.junit.Test;
import util.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by pengweijie on 2018/11/20
 */
public class ScoreDaoTest {
    ScoreDao scoreDao = DaoFactory.getScoreDaoInstance();
    @Test
    public void queryScoreByTeacherIdAndCourseId() throws Exception {
        scoreDao.queryScoreByTeacherIdAndCourseId(123, 1);
    }



    @Test
    public void queryScoreByTeacherId() throws Exception {
        scoreDao.queryScoreByTeacherId(123);
    }


    @Test
    public void getAllRowsAmount() throws Exception {
        System.out.println(scoreDao.getAllRowsAmount());
    }

    @Test
    public void getUserByCurrentPage() throws Exception {
        List<Score> scoreList = null;
        ScoreDao scoreDao = DaoFactory.getScoreDaoInstance();
        scoreList = scoreDao.getScoreByCurrentPage(1,3);
        System.out.println(scoreList);

    }

    @Test
    public void selectScoreByCourseName() throws Exception {
        List<Score> scoreList=null;
        ScoreDao scoreDao = DaoFactory.getScoreDaoInstance();
        scoreList = scoreDao.selectScoreByCourseName("数据结构");
        System.out.println(scoreList);

    }

    @Test
    public void selectScoreByStudentId() throws Exception {
        List<Score> scoreList=null;
        ScoreDao scoreDao = DaoFactory.getScoreDaoInstance();
        scoreList = scoreDao.selectScoreByStudentId(2);
        for (Score score :
                scoreList) {
            System.out.println(score);

        }
    }

    @Test
    public void insertScore() throws Exception {
        Score score = new Score(3, 2, "李四", "数据结构", 3);
        ScoreDao scoreDao = DaoFactory.getScoreDaoInstance();
        scoreDao.insertScore(score);

    }

    @Test
    public void deleteScore() throws Exception {
        ScoreDao scoreDao = DaoFactory.getScoreDaoInstance();

        List<Score> scoreList = scoreDao.selectScoreByStudentId(4);
        Score score = scoreList.get(0);
        scoreDao.deleteScore(score);

    }



}