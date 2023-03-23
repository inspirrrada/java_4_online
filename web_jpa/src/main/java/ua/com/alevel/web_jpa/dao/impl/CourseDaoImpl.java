package ua.com.alevel.web_jpa.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ua.com.alevel.web_jpa.dao.CourseDao;
import ua.com.alevel.web_jpa.persistance.entity.Course;

import java.util.Collection;
import java.util.List;

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
        return manager.createQuery("select c from Course c").getResultList();
    }

    @Override
    public Collection<Course> findAllByStudent(Long studentId) {
        return manager.createQuery("from Course c join c.students as courses_by_student where courses_by_student.id = :studentId")
                .setParameter("studentId", studentId)
                .getResultList();
    }

}
