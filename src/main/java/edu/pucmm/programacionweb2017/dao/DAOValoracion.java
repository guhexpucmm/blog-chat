package edu.pucmm.programacionweb2017.dao;

import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.entity.Valoracion;

import java.util.List;

public interface DAOValoracion extends DAO<Valoracion, Long> {
    @Override
    void insertar(Valoracion valoracion);

    @Override
    void actualizar(Valoracion valoracion);

    @Override
    void borrar(Valoracion valoracion);

    @Override
    Valoracion encontrarPorId(Long aLong);

    @Override
    List<Valoracion> encontrarTodos();

    Valoracion encontrarValoracion(Usuario usuario, Long id);
}
