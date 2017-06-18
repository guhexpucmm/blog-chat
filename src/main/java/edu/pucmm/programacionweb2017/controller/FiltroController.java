package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.service.ServiceUsuario;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.VistaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;

import static spark.Spark.halt;

public class FiltroController {
    private static final Logger logger = LoggerFactory.getLogger(FiltroController.class);

    private static ServiceUsuario serviceUsuario = new ServiceUsuario();

    public static Filter filtro = (request, response) -> {
        Usuario usuario = serviceUsuario.encontrarPorNombreUsuario(request.session().attribute("username"));

        if (usuario == null || !usuario.isAdministrador() || !usuario.isAutor()) {
            halt(403, new VistaUtil().render(null, Path.Template.NO_TIENE_PERMISOS));

            response.redirect(Path.Web.NO_TIENE_PERMISOS);
        }
    };
}
