package ua.com.alevel.persistance.dao;

import ua.com.alevel.persistance.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface BaseDao<E extends BaseEntity> {

    void create(E entity);
    void update(E entity);
    void delete(E entity);
    Optional<E> findById(Long id);
    Collection<E> findAll();
}
