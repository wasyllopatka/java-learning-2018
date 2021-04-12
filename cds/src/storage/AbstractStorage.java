package storage;

import java.util.List;

public interface AbstractStorage<T> {
    List<T> findAll();

    T findOnId(Integer id);

    T add(T entity);

    void update(T entity);

    void delete(int id);
}
