package edu.pucmm.programacionweb2017.service;

import edu.pucmm.programacionweb2017.dao.impl.DAOValoracionImpl;
import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.entity.Valoracion;

import java.util.List;

public class ServiceValoracion {
    private DAOValoracionImpl daoValoracion;

    public ServiceValoracion() {
        daoValoracion = new DAOValoracionImpl(Valoracion.class);
    }

    public void insertar(Valoracion valoracion) {
        daoValoracion.insertar(valoracion);
    }

    public void actualizar(Valoracion valoracion) {
        daoValoracion.actualizar(valoracion);
    }

    public void borrar(Valoracion valoracion) {
        daoValoracion.borrar(valoracion);
    }

    public Valoracion encontrarPorId(Long aLong) {
        return daoValoracion.encontrarPorId(aLong);
    }

    public List<Valoracion> encontrarTodos() {
        return daoValoracion.encontrarTodos();
    }

    public Valoracion encontrarValoracion(Usuario usuario, Long id) {
        return daoValoracion.encontrarValoracion(usuario, id);
    }
}
