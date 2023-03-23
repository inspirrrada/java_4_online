package ua.com.alevel.web_jpa.service;

import ua.com.alevel.web_jpa.persistance.entity.Course;
import ua.com.alevel.web_jpa.persistance.entity.Student;

import java.util.Collection;

public interface CourseService extends BaseService<Course>{

    void attachStudentToCourse(Student student);
    Collection<Course> findAllByStudent(Long studentId);
}
