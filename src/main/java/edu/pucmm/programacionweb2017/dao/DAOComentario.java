package edu.pucmm.programacionweb2017.dao;

import edu.pucmm.programacionweb2017.entity.Comentario;

import java.util.List;

public interface DAOComentario extends DAO<Comentario, Long> {
    @Override
    void insertar(Comentario comentario);

    @Override
    void actualizar(Comentario comentario);

    @Override
    void borrar(Comentario comentario);

    @Override
    Comentario encontrarPorId(Long aLong);

    @Override
    List<Comentario> encontrarTodos();
}
