package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.util.Path;
import spark.Route;

/**
 * Created by gusta on 17-Jun-17.
 */
public class SesionController {
    public static Route iniciarSesion = (request, response) -> {
        request.session(true);
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
