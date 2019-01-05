package main.vo;

import java.io.Serializable;

/**
 * Create by pengweijie on 2018/11/19
 */
public class Classes{
    private int classId;
    private String className;
    private String ofCollege;

    public Classes() {
    }

    public Classes(int classId, String className, String ofCollege) {
        this.classId = classId;
        this.className = className;
        this.ofCollege = ofCollege;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOfCollege() {
        return ofCollege;
    }

    public void setOfCollege(String ofCollege) {
        this.ofCollege = ofCollege;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", ofCollege='" + ofCollege + '\'' +
                '}';
    }
}
