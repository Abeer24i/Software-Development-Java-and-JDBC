package repositories;

import java.util.List;
import entities.Student;
import exceptions.PersistenceException;

public abstract class StudentRepository extends AbstractRepository<Student, Long> {

    public abstract List<Student> findByCourseId(Long courseId) throws PersistenceException;

    public abstract Integer courseCount(Long studentId) throws PersistenceException;

}
