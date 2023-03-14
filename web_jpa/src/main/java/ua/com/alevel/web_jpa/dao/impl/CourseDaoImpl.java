package ua.com.alevel.web_jpa.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.com.alevel.web_jpa.dao.CourseDao;
import ua.com.alevel.web_jpa.persistance.entity.Course;
import ua.com.alevel.web_jpa.persistance.entity.Student;

import java.util.Collection;

@Service
@Transactional
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void create(Course course) {
        manager.persist(course);
    }

    @Override
    public void update(Course course) {
        manager.merge(course);
    }

    @Override
    public void delete(Long id) {
        manager.createQuery("delete from Course where Course.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Course findById(Long id) {
        return manager.find(Course.class, id);
    }

    @Override
    public Collection<Course> findAll() {
        return manager.createQuery("from Course").getResultList();
    }

    @Override
    public Collection<Course> findAllByStudent(Student student) {
        return manager.createQuery("from Course join Course.students as courses_by_student where courses_by_student.id = :studentId")
                .setParameter("studentId", student.getId())
                .getResultList();
    }
}
