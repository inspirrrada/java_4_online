package ua.com.alevel.service;

import java.util.Collection;

public interface BaseService<E> {

    void create(E entity);
    void update(E entity);
    boolean delete(E entity);
    E findById(Long id);
    Collection<E> findAll();
}
