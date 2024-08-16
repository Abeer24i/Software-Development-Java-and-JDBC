package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Course implements Identifiable {

    private Long id;
    private String name;
    private String code;
    private String hours;
    private String grade;

    private List<StudentRef> studentList = new ArrayList<>();

    public Course() {
    }

    public Course(Long id, String name, String code, String hours, String grade) {
        this.hours = hours;
        this.code = code;
        this.name = name;
        this.grade = grade;
        this.id = id;
    }

    public Course(String name, String code, String hours, String grade) {
        this(null, code, name, hours, grade);
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String description) {
        this.grade = description;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
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
        return "Course{" + "id=" + id + ", name=" + name + ", code=" + code + ", hours=" + hours + ", grade=" + grade + ", studentList=" + studentList + '}';
    }



}
