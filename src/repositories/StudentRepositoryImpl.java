package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import dao.AbstractDAO;
import dao.MySQLRegistrationDAO;
import dao.MySQLStudentDAO;
import dao.RegistrationDAO;
import entities.Registration;
import entities.Student;
import exceptions.DAOException;
import exceptions.PersistenceException;

public class StudentRepositoryImpl extends StudentRepository {

    private RegistrationDAO regDAO;

    public StudentRepositoryImpl() {
        regDAO = new MySQLRegistrationDAO();
    }

    @Override
    public void removeById(Long studentId) throws PersistenceException {

        try {
            boolean isExists = contains(studentId);
            if (isExists) {
                List<Registration> regList = regDAO.findByStudentId(studentId);
                for (Registration reg : regList) {
                    regDAO.delete(reg.getId());
                }
            }

        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
        
       super.removeById(studentId);
    }

    @Override
    public List<Student> findByCourseId(Long courseId) throws PersistenceException {
        List<Student> studentList = new ArrayList<>();
        try {
            List<Registration> regList = regDAO.findByCourseId(courseId);
            for (Registration reg : regList) {
                Optional<Student> optionalStudent = findById(reg.getStudentId());
                if (optionalStudent.isPresent()) {
                    studentList.add(optionalStudent.get());
                }
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
        return studentList;
    }

    @Override
    public Integer courseCount(Long studentId) throws PersistenceException {
        Integer count = 0;
        try {
            count = regDAO.findByStudentId(studentId).size();
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
        return count;
    }

    @Override
    public AbstractDAO getAbstractDAO() {
        return new MySQLStudentDAO();
    }

}
