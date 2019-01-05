package main.DaoFactory;

import main.dao.*;
import main.dao.daoImpl.*;

/**
 * Create by pengweijie on 2018/11/19
 */
public class DaoFactory {
    public static StudentDao getStudentDaoInstance() {
        return new StudentDaoImpl();
    }
    public static ClassesDao getClassesDaoInstance() {
        return new ClassesDaoImpl();
    }
    public static TeacherDao getTeacherDaoInstance() {
        return new TeacherDaoImpl();
    }

    public static ScoreDao getScoreDaoInstance() { return new ScoreDaoImpl();    }

    public static UserDao getUserDaoInstance() { return new UserDaoImpl();}


}
