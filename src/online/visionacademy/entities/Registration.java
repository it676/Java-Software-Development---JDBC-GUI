package online.visionacademy.entities;

public class Registration implements Identifiable {

    private Long id;
    private Long studentId;
    private Long courseId;

    public Registration() {
    }

    public Registration(Long id, Long studentId, Long courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Registration(Long studentId, Long courseId) {
        this(null, studentId, courseId);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Registration{" + "id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + '}';
    }

}
