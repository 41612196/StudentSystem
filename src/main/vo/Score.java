package main.vo;

import java.io.Serializable;

/**
 * Create by pengweijie on 2018/11/19
 */
public class Score {
    private int studentId;
    private int courseId;
    private String studentName;
    private String courseName;
    private int grade;

    public Score() {
    }

    public Score(int studentId, int courseId, String studentName, String courseName, int grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Score{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", grade=" + grade +
                '}';
    }
}
