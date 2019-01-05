package main.service.serviceImpl;

import main.DaoFactory.DaoFactory;
import main.dao.ScoreDao;
import main.service.StudentService;
import main.vo.Score;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/20
 */
public class StudentServiceImpl implements StudentService {
    @Override
    public List<Score> queryScore(int studentId) {
        ScoreDao scoreDao = DaoFactory.getScoreDaoInstance();
        return scoreDao.selectScoreByStudentId(studentId);
    }
}
