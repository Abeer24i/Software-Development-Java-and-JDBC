package dao;

import datasource.ConnectionFactory;
import datasource.DataSourceType;
import entities.Course;
import exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import support.QueryBuilder;

public class MySQLCourseDAO extends CourseDAO {

    private static final String TABLE_NAME = "courses";
    private static final String[] cols = {"code", "name", "hours", "grade"};

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getCols() {
        return cols;
    }

    @Override
    protected void setStatementParas(PreparedStatement ps, Course course) throws DAOException {
        boolean isInsert = course.getId() == null;
        try {
            ps.setString(1, course.getCode());
            ps.setString(1, course.getName());
            ps.setString(2, course.getHours());
            ps.setString(3, course.getGrade());

            if (!isInsert) {
                ps.setLong(4, course.getId());
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    protected Course mapObject(ResultSet rs) throws DAOException {
        Course course = new Course();
        try {
            course.setId(rs.getLong("id"));
            course.setCode(rs.getString("code"));
            course.setName(rs.getString("name"));
            course.setHours(rs.getString("hours"));
            course.setGrade(rs.getString("grade"));

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return course;
    }

    @Override
    protected List<Course> mapAllObjects(ResultSet rs) throws DAOException {
        List<Course> courseList = new ArrayList<>();
        try {
            while (rs.next()) {
                Course course = mapObject(rs);
                courseList.add(course);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return courseList;
    }

}
