package online.visionacademy.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Course implements Identifiable {

    private Long id;
    private String code;
    private String name;
    private String description;

    private List<StudentRef> studentList = new ArrayList<>();

    public Course() {
    }

    public Course(Long id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Course(String code, String name, String description) {
        this(null, code, name, description);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

     public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student can't be null");
        }
        if (student.getId() == null) {
            throw new IllegalArgumentException("Student id can't be null");
        }
        if (isExists(student.getId())) {
            throw new IllegalArgumentException("Student with id '" + student.getId() + "' already added in the course");
        }
        this.studentList.add(new StudentRef(student.getId()));
    }

    public void removeStudentById(Long studentId) {
        studentList = studentList.stream().filter(std -> !std.getStudentId().equals(studentId)).collect(Collectors.toList());
    }

    public void addStudentList(List<Student> studentList) {
        for (Student student : studentList) {
            addStudent(student);
        }
    }

    public List<Long> getStudentIds() {
        return studentList.stream()
                .map(StudentRef::getStudentId)
                .collect(Collectors.toList());
    }

    public void setStudentIds(List<Long> ids) {
        for (Long stdId : ids) {
            this.studentList.add(new StudentRef(stdId));
        }
    }

    private boolean isExists(Long id) {
        if (studentList.stream().anyMatch((std) -> (std.getStudentId().equals(id)))) {
            return true;
        }
        return false;
    }
    

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", studentList=" + studentList + '}';
    }

}
