package ua.com.alevel.web_jpa.service;

import java.util.Collection;

public interface BaseService<E> {

    void create(E entity);
    void update(E entity);
    void delete(Long id);
    E findById(Long id);
    Collection<E> findAll();
}
