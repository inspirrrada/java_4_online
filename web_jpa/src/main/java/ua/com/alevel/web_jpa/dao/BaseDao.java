package ua.com.alevel.web_jpa.dao;

import ua.com.alevel.web_jpa.persistance.entity.BaseEntity;

import java.util.Collection;

public interface BaseDao<E extends BaseEntity> {

    void create(E entity);
    void update(E entity);
    void delete(Long id);
    E findById(Long id);
    Collection<E> findAll();
}
