package main.dao;

import main.vo.Classes;
import main.vo.Student;

import java.util.List;

/**
 * Create by pengweijie on 2018/11/21
 */
public interface ClassesDao {
    /**
     *通过班级id查询班级
     * @param classId
     * @return
     */
    Classes selectClassByClassId(int classId);
    /**
     * 添加新的班级
     * @param classes
     */
    boolean insertClass(Classes classes);
    /**
     * 删除某个班级
     */
    boolean deleteClass(Classes classes);

    List<Classes> getAllClasses();

    boolean addStudentToClass(Student student, Classes classes);
}
