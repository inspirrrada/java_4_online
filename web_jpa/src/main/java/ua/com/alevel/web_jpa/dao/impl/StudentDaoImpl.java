package ua.com.alevel.web_jpa.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ua.com.alevel.web_jpa.dao.StudentDao;
import ua.com.alevel.web_jpa.persistance.entity.Student;

import java.util.Collection;

@Service
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void create(Student student) {
        manager.persist(student);
    }

    @Override
    public void update(Student student) {
        manager.merge(student);
    }

    @Override
    public void delete(Long id) {
        manager.createQuery("delete from Student where Student.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Student findById(Long id) {
        return manager.find(Student.class, id);
    }

    @Override
    public Collection<Student> findAll() {
        return manager.createQuery("select s from Student s", Student.class).getResultList();
    }

    @Override
    public Collection<Student> findAllByCourse(Long courseId) {
        return manager.createQuery("from Student s join s.courses as students_by_course where students_by_course.id = :courseId")
                .setParameter("courseId", courseId)
                .getResultList();
    }
}
