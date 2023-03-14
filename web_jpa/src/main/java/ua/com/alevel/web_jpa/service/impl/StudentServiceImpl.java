package ua.com.alevel.web_jpa.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.web_jpa.dao.StudentDao;
import ua.com.alevel.web_jpa.persistance.entity.Course;
import ua.com.alevel.web_jpa.persistance.entity.Student;
import ua.com.alevel.web_jpa.service.StudentService;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void create(Student student) {
        studentDao.create(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(Long id) {
        studentDao.delete(id);
    }

    @Override
    public Student findById(Long id) {
        return studentDao.findById(id);
    }

    @Override
    public Collection<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Collection<Student> findAllByCourse(Course course) {
        return studentDao.findAllByCourse(course);
    }
}
