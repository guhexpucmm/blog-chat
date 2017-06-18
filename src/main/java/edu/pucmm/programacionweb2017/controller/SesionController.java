package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

public class SesionController {
    private static final Logger logger = LoggerFactory.getLogger(SesionController.class);

    public static Route iniciarSesion = (request, response) -> {
        request.session(true);
        request.session().maxInactiveInterval(60);//60 segundos para invalidar la sesion si esta inactivo
        request.session().attribute("username", request.queryParams("User"));
        request.session().attribute("password", request.queryParams("Pass"));

        response.redirect(Path.Web.INICIO);
        return null;
    };

    public static Route cerrarSesion = (request, response) -> {
        request.session().invalidate();

        response.redirect(Path.Web.INICIO);
        return null;
    };
}
