package online.visionacademy.mappers;

import online.visionacademy.dtos.StudentDTO;
import online.visionacademy.entities.Student;

public interface StudentMapper {

    public abstract StudentDTO toDTO(Student std);
}
