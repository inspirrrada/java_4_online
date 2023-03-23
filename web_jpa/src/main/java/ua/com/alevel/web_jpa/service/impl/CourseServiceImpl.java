package ua.com.alevel.web_jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.web_jpa.dao.CourseDao;
import ua.com.alevel.web_jpa.persistance.entity.Course;
import ua.com.alevel.web_jpa.persistance.entity.Student;
import ua.com.alevel.web_jpa.service.CourseService;

import java.util.Collection;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void create(Course course) {
        courseDao.create(course);
    }

    @Override
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    public void delete(Long id) {
        courseDao.delete(id);
    }

    @Override
    public Course findById(Long id) {
        return courseDao.findById(id);
    }

    @Override
    public Collection<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public void attachStudentToCourse(Student student) {

    }

    @Override
    public Collection<Course> findAllByStudent(Long studentId) {
        return courseDao.findAllByStudent(studentId);
    }
}
