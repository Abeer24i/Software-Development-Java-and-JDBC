package mappers;

import dtos.StudentDTO;
import entities.Student;

public interface StudentMapper {

    public abstract StudentDTO toDTO(Student std);
}
