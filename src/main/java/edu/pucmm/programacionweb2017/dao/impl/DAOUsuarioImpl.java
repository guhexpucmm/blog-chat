package edu.pucmm.programacionweb2017.dao.impl;

import edu.pucmm.programacionweb2017.dao.DAOUsuario;
import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DAOUsuarioImpl extends DAOImpl<Usuario, Long> implements DAOUsuario {
    private static final Logger logger = LoggerFactory.getLogger(DAOUsuarioImpl.class);

    public DAOUsuarioImpl(Class<Usuario> usuarioClass) {
        super(usuarioClass);
    }

    @Override
    public void insertar(Usuario usuario) {
        super.insertar(usuario);
    }

    @Override
    public void actualizar(Usuario usuario) {
        super.actualizar(usuario);
    }

    @Override
    public void borrar(Usuario usuario) {
        super.borrar(usuario);
    }

    @Override
    public Usuario encontrarPorId(Long aLong) {
        return super.encontrarPorId(aLong);
    }

    @Override
    public List<Usuario> encontrarTodos() {
        return super.encontrarTodos();
    }

    @Override
    public Usuario encontrarPorNombreUsuario(String nombreUsuario) {
        Session session = null;
        Transaction transaction = null;
        Query query = null;

        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            query = session.createQuery("from Usuario where username = :username").setParameter("username", nombreUsuario);

            return (Usuario) query.uniqueResult();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.debug("Error al ejecutar un select el objeto en la base de datos.", e);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Usuario existe(String username, String contrasena) {
        Session session = null;
        Transaction transaction = null;
        Query query = null;

        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            query = session.createQuery("from Usuario where username = :username and password = :password").setParameter("username", username).setParameter("password", contrasena);

            return (Usuario) query.uniqueResult();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.debug("Error al ejecutar un select el objeto en la base de datos.", e);
            return null;
        } finally {
            session.close();
        }
    }
}
