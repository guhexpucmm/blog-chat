package edu.pucmm.programacionweb2017.dao;

import edu.pucmm.programacionweb2017.entity.Etiqueta;

import java.util.List;

public interface DAOEtiqueta extends DAO<Etiqueta, Long> {
    @Override
    void insertar(Etiqueta etiqueta);

    @Override
    void actualizar(Etiqueta etiqueta);

    @Override
    void borrar(Etiqueta etiqueta);

    @Override
    Etiqueta encontrarPorId(Long aLong);

    @Override
    List<Etiqueta> encontrarTodos();

    Etiqueta encontrarPorEtiqueta(String etiqueta);
}
