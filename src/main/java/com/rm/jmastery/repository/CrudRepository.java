package com.rm.jmastery.repository;

import java.util.List;

public interface CrudRepository<T, K> {

    List<T> findAll();

    T findById(K id);

    T save(T employeeDto);

    T update(T employeeDto);

    void deleteById(Long id);
}
