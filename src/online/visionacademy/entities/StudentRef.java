package online.visionacademy.entities;

public class StudentRef {

    private Long studentId;

    public StudentRef(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "StudentRef{" + "studentId=" + studentId + '}';
    }

}
