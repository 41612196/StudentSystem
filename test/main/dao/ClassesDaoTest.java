package main.dao;

import main.DaoFactory.DaoFactory;
import main.vo.Classes;
import org.junit.Test;


/**
 * Create by pengweijie on 2018/11/21
 */
public class ClassesDaoTest {
    ClassesDao classesDao = DaoFactory.getClassesDaoInstance();

    @Test
    public void selectClassByClassId() throws Exception {
        Classes classes = classesDao.selectClassByClassId(1);
        System.out.println(classes);



    }

    @Test
    public void insertClass() throws Exception {
        Classes classes = new Classes(7, "化学", "化学院");
        classesDao.insertClass(classes);

    }

    @Test
    public void deleteClass() throws Exception {
        Classes classes = new Classes(7, "化学", "化学院");
        classesDao.deleteClass(classes);
    }

}