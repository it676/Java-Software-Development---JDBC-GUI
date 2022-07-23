package online.visionacademy.dtos;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO {

    private Long nationalityId;
    private String name;
    private List<String> courseList = new ArrayList<>();

    public StudentDTO() {
    }

    public StudentDTO(Long nationalityId, String name) {
        this.nationalityId = nationalityId;
        this.name = name;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "StudentDTO{" + "nationalityId=" + nationalityId + ", name=" + name + ", courseList=" + courseList + '}';
    }

}
