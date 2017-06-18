package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.entity.Articulo;
import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.entity.Valoracion;
import edu.pucmm.programacionweb2017.service.ServiceArticulo;
import edu.pucmm.programacionweb2017.service.ServiceUsuario;
import edu.pucmm.programacionweb2017.service.ServiceValoracion;
import edu.pucmm.programacionweb2017.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

public class ValoracionController {
    private static final Logger logger = LoggerFactory.getLogger(ValoracionController.class);

    private static ServiceUsuario serviceUsuario = new ServiceUsuario();
    private static ServiceArticulo serviceArticulo = new ServiceArticulo();
    private static ServiceValoracion serviceValoracion = new ServiceValoracion();

    public static Route valoracion = (request, response) -> {
        Usuario usuario = serviceUsuario.encontrarPorNombreUsuario(request.session().attribute("username"));
        Articulo articulo = serviceArticulo.encontrarPorId(Long.parseLong(request.queryParams("articulo")));
        boolean like = Boolean.valueOf(String.valueOf(request.queryParams("valoracion")));

        Valoracion v = serviceValoracion.encontrarValoracion(usuario, articulo.getId());

        if (v == null) {
            v = new Valoracion(like, usuario, articulo);

            serviceValoracion.insertar(v);

            response.redirect(Path.Web.VER_ARTICULO + "?id=" + articulo.getId());
        } else {
            response.redirect(Path.Web.VER_ARTICULO + "?id=" + articulo.getId());
        }

        return null;
    };
}
