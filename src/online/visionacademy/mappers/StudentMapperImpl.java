package online.visionacademy.mappers;

import online.visionacademy.dtos.StudentDTO;
import online.visionacademy.entities.Student;

public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO toDTO(Student student) {
        StudentDTO stdDTO = new StudentDTO();
        stdDTO.setNationalityId(student.getNationalityId());
        stdDTO.setName(student.getFirstName() + " " + student.getLastName());
        return stdDTO;
    }

}
