package edu.pucmm.programacionweb2017.dao;

import java.util.List;

public interface DAO<T,K extends Long> {
    void insertar(T t);

    void actualizar(T t);

    void borrar(T t);

    void merge(T t);

    T encontrarPorId(K k);

    List<T> encontrarTodos();
}
