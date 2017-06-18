package edu.pucmm.programacionweb2017.main;

import edu.pucmm.programacionweb2017.chat.ChatWebSocketHandler;
import edu.pucmm.programacionweb2017.controller.*;
import edu.pucmm.programacionweb2017.database.BootstrapServices;
import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.service.ServiceUsuario;
import edu.pucmm.programacionweb2017.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private Main() {
        logger.info("Iniciando aplicacion web.");
        enableDebugScreen();
        setConfiguraciones();
        setServidor();
        setRedireccion();
        setRutas();
        setUsuario();
    }

    private static void setUsuario() {
        logger.info("Configurando usuario por defecto.");
        Usuario usuario = new Usuario(
                "admin",
                "Gustavo",
                "admin",
                true,
                true
        );

        ServiceUsuario serviceUsuario = new ServiceUsuario();

        if (serviceUsuario.encontrarPorNombreUsuario(usuario.getUsername()) == null)
            serviceUsuario.insertar(usuario);
    }

    public static void main(String[] args) {
        webSocket(Path.Web.INICIO, ChatWebSocketHandler.class);
        new Main();
    }

    private void setConfiguraciones() {
        logger.info("Inicializando configuracion.");

        logger.info("Configurando carpeta de recursos publica.");
        staticFileLocation("/public");
    }

    private void setServidor() {
        logger.info("Iniciando el servidor h2.");
        BootstrapServices.iniciarServer();
        logger.info("Servidor inicializado.");
    }

    private void setRedireccion() {
        logger.info("Configurando redireccion");
        get("/", (request, response) -> {
            response.redirect(Path.Web.INICIO);
            return null;
        });
    }

    private void setRutas() {
        get(Path.Web.INICIO, InicioController.paginaInicio);
        post(Path.Web.INICIAR_SESION, SesionController.iniciarSesion);
        post(Path.Web.CERRAR_SESION, SesionController.cerrarSesion);

        get(Path.Web.VER_ARTICULO, ArticuloController.paginaVerArticulo);

        before(Path.Web.NUEVO_ARTICULO, FiltroController.filtro);
        get(Path.Web.NUEVO_ARTICULO, ArticuloController.paginaNuevoArticulo);
        post(Path.Web.NUEVO_ARTICULO, ArticuloController.crearNuevoArticulo);
        before(Path.Web.ELIMINAR_ARTICULO, FiltroController.filtro);
        get(Path.Web.ELIMINAR_ARTICULO, ArticuloController.eliminarArticulo);
        before(Path.Web.MODIFICAR_ARTICULO, FiltroController.filtro);
        get(Path.Web.MODIFICAR_ARTICULO, ArticuloController.paginaModificarArticulo);
        post(Path.Web.MODIFICAR_ARTICULO, ArticuloController.moficarArticulo);

        get(Path.Web.VALORACION, ValoracionController.valoracion);

        before(Path.Web.NUEVO_USUARIO, FiltroController.filtro);
        get(Path.Web.NUEVO_USUARIO, UsuarioController.paginaNuevoUsuario);
        post(Path.Web.NUEVO_USUARIO, UsuarioController.crearNuevoUsuario);

        get(Path.Web.NO_TIENE_PERMISOS, NoTienePermisosController.paginaNoTienePermisos);
        get("*", NoEncontradoController.paginaNoEncontrada);
    }
}
