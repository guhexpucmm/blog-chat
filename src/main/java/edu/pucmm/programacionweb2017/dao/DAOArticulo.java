package edu.pucmm.programacionweb2017.dao;

import edu.pucmm.programacionweb2017.entity.Articulo;
import edu.pucmm.programacionweb2017.entity.Etiqueta;
import edu.pucmm.programacionweb2017.entity.Valoracion;

import java.util.List;

public interface DAOArticulo extends DAO<Articulo, Long> {
    @Override
    void actualizar(Articulo articulo);

    @Override
    void borrar(Articulo articulo);

    @Override
    Articulo encontrarPorId(Long aLong);

    @Override
    List<Articulo> encontrarTodos();

    List<Valoracion> obtenerValoracionesPositivas(Articulo articulo);

    List<Valoracion> obtenerValoracionesNegativas(Articulo articulo);

    List<Articulo> obtenerArticulosDadaUnaEtiqueta(Etiqueta etiqueta);

    List<Articulo> obtenerArticulosPaginacion(int inicio, int fin);

    Long totalArticulos();
}
