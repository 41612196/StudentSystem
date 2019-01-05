package main.vo;

/**
 * Create by pengweijie on 2018/11/20
 */
public class Course {
    private int courseId;
    private String courseName;
    private float credit;           //学分


    public Course() {
    }

    public Course(int courseId, String courseName, float credit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }



    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +

                '}';
    }
}
