package com.intellij;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    boolean insert(T objet);
    boolean update(int id, String string);
    boolean delete(int id);
    T getObject(int id);
}
