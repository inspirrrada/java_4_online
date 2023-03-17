package ua.com.alevel.service;

import java.util.Collection;
import java.util.Optional;

public interface BaseService<E> {

    void create(E entity);
    void update(E entity);
    void delete(E entity);
    E findById(Long id);
    Collection<E> findAll();
}
