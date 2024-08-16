package mappers;

import dtos.StudentDTO;
import entities.Student;

public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO toDTO(Student student) {
        StudentDTO stdDTO = new StudentDTO();
        stdDTO.setNationalityId(student.getNationalityId());
        stdDTO.setName(student.getFirstName() + " " + student.getLastName());
        return stdDTO;
    }

}
