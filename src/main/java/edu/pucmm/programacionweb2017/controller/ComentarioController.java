package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.entity.Articulo;
import edu.pucmm.programacionweb2017.entity.Comentario;
import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.service.ServiceArticulo;
import edu.pucmm.programacionweb2017.service.ServiceComentario;
import edu.pucmm.programacionweb2017.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ComentarioController {
    private static final Logger logger = LoggerFactory.getLogger(ComentarioController.class);
    private static final ServiceArticulo serviceArticulo = new ServiceArticulo();
    private static final ServiceComentario serviceComentario = new ServiceComentario();

    public static Route crearComentario = (request, response) -> {
        Map<String, Object> map = new HashMap<>();
        Articulo articulo = serviceArticulo.encontrarPorId(Long.parseLong(request.queryParams("articulo")));

        String c = request.queryMap("Coment").value();
        Usuario usuario = articulo.getAutor();

        Comentario comentario = new Comentario(c, usuario, articulo);

        articulo.getListaComentarios().add(comentario);

        serviceArticulo.actualizar(articulo);

        response.redirect(Path.Web.VER_ARTICULO + "?id=" + articulo.getId());

        return null;
    };

    public static Route borrarComentario = (request, response) -> {
        Articulo articulo = serviceArticulo.encontrarPorId(Long.valueOf(request.queryParams("articulo")));
        Comentario comentario = serviceComentario.encontrarPorId(Long.valueOf(request.queryParams("comentario")));

        articulo.getListaComentarios().removeIf(c -> c.getId() == comentario.getId());

        serviceArticulo.actualizar(articulo);

        response.redirect(Path.Web.VER_ARTICULO + "?id=" + articulo.getId());

        return null;
    };
}
