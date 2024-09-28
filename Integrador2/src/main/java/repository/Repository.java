package repository;

import java.util.List;

public interface Repository<T> {
    void insert(T t);

    T selectById(int id);

    List<T> selectAll();

    boolean update(T t);

    boolean delete(int id);
}
