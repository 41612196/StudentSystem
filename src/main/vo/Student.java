package main.vo;

import java.io.Serializable;

/**
 * Create by pengweijie on 2018/11/19
 */
public class Student{
    private int studentId;
    private String studentName;
    private String sex;
    private String major;
    private int age;
    private int yearSchool;
    private String telephone;
    private String address;
    private Integer classId;
    private String className;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student(int studentId, String studentName, String sex, String major, int age, int yearSchool) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.major = major;
        this.age = age;
        this.yearSchool = yearSchool;
    }

    public Student(int studentId, String studentName, String sex, String major, int age, int yearSchool, String telephone, String address) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.major = major;
        this.age = age;
        this.yearSchool = yearSchool;
        this.telephone = telephone;
        this.address = address;
    }

    public Student() {
    }



    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYearSchool() {
        return yearSchool;
    }

    public void setYearSchool(int yearSchool) {
        this.yearSchool = yearSchool;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", sex='" + sex + '\'' +
                ", major='" + major + '\'' +
                ", age=" + age +
                ", yearSchool=" + yearSchool +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
