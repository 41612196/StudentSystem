package main.vo;

/**
 * Create by pengweijie on 2018/12/6
 */
public class CourseArranged {
    private String className;
    private String courseName;
    private String teacherName;
    private int classId;
    private int courseId;
    private int teacherId;

    public CourseArranged() {
    }

    public CourseArranged(String className, String courseName, String teacherName, int classId, int courseId, int teacherId) {
        this.className = className;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.classId = classId;
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "CourseArranged{" +
                "className='" + className + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", classId=" + classId +
                ", courseId=" + courseId +
                ", teacherId=" + teacherId +
                '}';
    }
}
