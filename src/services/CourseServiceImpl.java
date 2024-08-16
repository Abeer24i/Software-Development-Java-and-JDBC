package services;

import java.util.List;
import java.util.Optional;
import dtos.CourseDTO;
import entities.Course;
import exceptions.CourseNotFoundException;
import exceptions.PersistenceException;
import exceptions.ServiceException;
import exceptions.ValidationException;
import repositories.CourseRepository;
import repositories.CourseRepositoryImpl;
import repositories.StudentRepository;
import repositories.StudentRepositoryImpl;
import validators.LengthValidator;
import validators.NullValidator;
import validators.Validator;

public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private StudentRepository stdRepository;

    public CourseServiceImpl() {
        courseRepository = new CourseRepositoryImpl();
        stdRepository = new StudentRepositoryImpl();
    }

    @Override
    public Course add(Course course) throws ServiceException {
        checkNull("Course", course);
        validate(course);
        String code = course.getCode();
        try {
            if (!courseRepository.findByColumn("code", code).isEmpty()) {
                throw new ValidationException("Course with code " + code + " already exists.");
            }

            //check if all students exist
            for (Long id : course.getStudentIds()) {
                if (!stdRepository.contains(id)) {
                    throw new ValidationException("Student with id " + id + " does not exist.");
                }
            }

            return courseRepository.add(course);

        } catch (PersistenceException ex) {
            System.out.println(ex);
            throw new ServiceException("Could not save course.");
        }

    }

    @Override
    public Course getById(Long id) throws ServiceException, CourseNotFoundException {
        try {
            checkNull("id", id);
            Optional<Course> optionalCourse = courseRepository.findById(id);
            if (!optionalCourse.isPresent()) {
                throw new CourseNotFoundException("Course with id " + id + " not found.");
            }
            Course course = optionalCourse.get();
            return course;
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not fetch course.");
        }
    }

    @Override
    public Course getByCode(String code) throws ServiceException, CourseNotFoundException {
        try {
            checkNull("Code", code);
            List<Course> courseList = courseRepository.findByColumn("code", code);
            if (courseList.isEmpty()) {
                throw new CourseNotFoundException("Course with code " + code + " not found.");
            }
            return courseList.get(0); //fetch first course
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not fetch course.");
        }

    }

    @Override
    public List<Course> getAll() throws ServiceException {
        try {
            return courseRepository.findAll();
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not fetch courses.");
        }
    }

    @Override
    public List<Course> getAllByStudent(Long id) throws ServiceException {
        try {
            checkNull("id", id);
            return courseRepository.findByStudentId(id);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not fetch courses.");
        }
    }

    @Override
    public boolean contains(Long id) throws ServiceException {
        try {
            checkNull("id", id);
            return courseRepository.contains(id);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not check course.");
        }
    }

    @Override
    public Integer count() throws ServiceException {
        try {
            return courseRepository.count();
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not count courses.");
        }
    }

    @Override
    public Integer studentCount(Long id) throws ServiceException {
        try {
            return courseRepository.studentCount(id);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not count students.");
        }
    }

    @Override
    public Course update(Course course) throws ServiceException, CourseNotFoundException {
        checkNull("Course", course);
        validate(course);

        Long id = course.getId();
        if (!contains(id)) {
            throw new CourseNotFoundException("Can't update course, course with id " + id + " not found");
        }
        try {
            //check if all students exist
            for (Long stdId : course.getStudentIds()) {
                if (!stdRepository.contains(stdId)) {
                    throw new ValidationException("Student with id " + id + " does not exist.");
                }
            }
            return courseRepository.update(course);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not update course");
        }
    }

    @Override
    public void removeById(Long id) throws ServiceException, CourseNotFoundException {
        try {
            if (!contains(id)) {
                throw new CourseNotFoundException("Can't remove course, course with id " + id + " not found");
            }
            courseRepository.removeById(id);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not remove course.");
        }
    }

    @Override
    public void remove(Course course) throws ServiceException, CourseNotFoundException {
        checkNull("Course", course);
        removeById(course.getId());
    }

    @Override
    public void removeAllById(List<Long> ids) throws ServiceException, CourseNotFoundException {
        for (Long id : ids) {
            removeById(id);
        }
    }

    private void validate(Course course) {

        String code = course.getCode();
        String name = course.getName();
        String grade = course.getGrade();

        //check nulls
        checkNull("Code", code);
        checkNull("Name", name);
        checkNull("Description", grade);

        //validate code id
        LengthValidator validator = new LengthValidator("Code", code, 5, 6);
        validator.validate();

        //validate name
        validator.setValues("Name", name, 3, 50);
        validator.validate();

        //validate description
        validator.setValues("Description", grade, 3, 1000);
        validator.validate();
        validator = null;
    }

    private void checkNull(String field, Object obj) {
        Validator validator = new NullValidator(field, obj);
        validator.validate();
        validator = null;
    }

    @Override
    public CourseDTO getCourseWithStudents(Long id) throws ServiceException, CourseNotFoundException {
        return null;
    }
}
