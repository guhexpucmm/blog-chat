package edu.pucmm.programacionweb2017.service;

import edu.pucmm.programacionweb2017.dao.impl.DAOUsuarioImpl;
import edu.pucmm.programacionweb2017.entity.Usuario;

import java.util.List;

public class ServiceUsuario {
    DAOUsuarioImpl daoUsuario;

    public ServiceUsuario() {
        daoUsuario = new DAOUsuarioImpl(Usuario.class);
    }

    public void insertar(Usuario usuario) {
        daoUsuario.insertar(usuario);
    }

    public void actualizar(Usuario usuario) {
        daoUsuario.actualizar(usuario);
    }

    public void borrar(Usuario usuario) {
        daoUsuario.borrar(usuario);
    }

    public Usuario encontrarPorId(Long aLong) {
        return daoUsuario.encontrarPorId(aLong);
    }

    public List<Usuario> encontrarTodos() {
        return daoUsuario.encontrarTodos();
    }

    public Usuario encontrarPorNombreUsuario(String nombreUsuario) {
        return daoUsuario.encontrarPorNombreUsuario(nombreUsuario);
    }

    public Usuario existe(String username, String contrasena) {
        return daoUsuario.existe(username, contrasena);
    }
}
