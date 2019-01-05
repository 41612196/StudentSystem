package main.vo;

/**
 * Create by pengweijie on 2018/11/22
 */
public class StudentClass {
    private int studentId;
    private String studentName;
    private int classId;
    private String className;

    public StudentClass() {
    }

    public StudentClass(int studentId, String studentName, int classId, String className) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.classId = classId;
        this.className = className;
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

    @Override
    public String toString() {
        return "StudentClass{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                '}';
    }
}
