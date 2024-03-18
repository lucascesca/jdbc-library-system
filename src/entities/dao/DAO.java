package entities.dao;

import java.util.List;

public interface DAO<T> {
    void insert(T obj);
    void update(T obj);
    void delete(T obj);
    T find(T obj);
    List<? extends T> findAll();
}
