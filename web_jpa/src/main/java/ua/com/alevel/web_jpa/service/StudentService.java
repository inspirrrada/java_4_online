package ua.com.alevel.web_jpa.service;

import ua.com.alevel.web_jpa.persistance.entity.Course;
import ua.com.alevel.web_jpa.persistance.entity.Student;

import java.util.Collection;

public interface StudentService extends BaseService<Student> {

    Collection<Student> findAllByCourse(Course course);
}
