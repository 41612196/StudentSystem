package main.vo;

import java.io.Serializable;

/**
 * Create by pengweijie on 2018/11/19
 */
public class Teacher {
    private int teacherId;
    private String teacherName;
    private String sex;
    private String college;
    private String professionalTitle;
    private String degree;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Teacher() {
    }

    public Teacher(int teacherId, String teacherName, String sex, String college, String professionalTitle, String degree) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.sex = sex;
        this.college = college;
        this.professionalTitle = professionalTitle;
        this.degree = degree;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", sex='" + sex + '\'' +
                ", college='" + college + '\'' +
                ", professionalTitle='" + professionalTitle + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
