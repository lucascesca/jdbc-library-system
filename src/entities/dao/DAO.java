package entities.dao;

import java.util.List;

public interface DAO<T> {
    void insert(T obj);
    void update(T obj);
    void deleteById(Integer id);
    T findById(Integer id);
    List<? extends T> findAll();
}
