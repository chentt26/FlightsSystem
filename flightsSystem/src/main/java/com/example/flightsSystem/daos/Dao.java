package com.example.flightsSystem.daos;

import java.util.List;

public interface Dao <T>{
    T get(int id);
    List<T>getAll();
    void add(T t);
    void remove(T t);
    void update(T t);

}
