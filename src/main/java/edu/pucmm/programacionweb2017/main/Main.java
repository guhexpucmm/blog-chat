package edu.pucmm.programacionweb2017.main;

import edu.pucmm.programacionweb2017.controller.ArticuloController;
import edu.pucmm.programacionweb2017.controller.InicioController;
import edu.pucmm.programacionweb2017.controller.SesionController;
import edu.pucmm.programacionweb2017.database.BootstrapServices;
import edu.pucmm.programacionweb2017.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Session;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    private final Logger logger = LoggerFactory.getLogger(Main.class);

    public Main() {
        logger.info("Iniciando aplicacion web.");
        enableDebugScreen();
        setConfiguraciones();
        setServidor();
        setRedireccion();
        //setFilter();
        setRutas();
    }

    private void setConfiguraciones() {
        logger.info("Inicializando configuracion.");

        logger.info("Configurando carpeta de recursos publica.");
        staticFileLocation("/public");
    }

    private void setServidor() {
        logger.info("Iniciando el servidor h2.");
        BootstrapServices bootstrapServices = new BootstrapServices();
        bootstrapServices.iniciarServer();
    }

    private void setRedireccion() {
        get("/", (request, response) -> {
            response.redirect(Path.Web.INICIO);
            return null;
        });
    }

    private void setFilter() {
        logger.info("Configurando filtro");
        before("/*/", (request, response) -> {
            Session session = request.session(true);
            boolean auth = session.attribute(Path.Web.AUTH_STATUS) != null ?
                    session.attribute(Path.Web.AUTH_STATUS) : false;

            logger.info("Status = " + auth);

            if (!auth) {
                logger.warn("Area segura, necesita iniciar sesion.");
                response.redirect(Path.Web.INICIO);
                halt(401);
            }
        });
    }

    public void setRutas() {
        get(Path.Web.INICIO, InicioController.paginaInicio);
        post(Path.Web.INICIAR_SESION, SesionController.iniciarSesion);
        post(Path.Web.CERRAR_SESION, SesionController.cerrarSesion);

        get(Path.Web.VER_ARTICULO, ArticuloController.paginaVerArticulo);
        get(Path.Web.NUEVO_ARTICULO, ArticuloController.paginaNuevoArticulo);
    }

    public static void main(String[] args) {
        new Main();
    }
}
