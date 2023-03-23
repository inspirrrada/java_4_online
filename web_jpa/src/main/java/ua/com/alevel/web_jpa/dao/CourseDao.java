package ua.com.alevel.web_jpa.dao;

import ua.com.alevel.web_jpa.persistance.entity.Course;
import ua.com.alevel.web_jpa.persistance.entity.Student;

import java.util.Collection;

public interface CourseDao extends BaseDao<Course> {

    Collection<Course> findAllByStudent(Long studentId);
}
