package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.entity.Articulo;
import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.service.ServiceArticulo;
import edu.pucmm.programacionweb2017.service.ServiceComentario;
import edu.pucmm.programacionweb2017.service.ServiceUsuario;
import edu.pucmm.programacionweb2017.service.ServiceValoracion;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.VistaUtil;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gusta on 17-Jun-17.
 */
public class ArticuloController {

    private static ServiceArticulo serviceArticulo = new ServiceArticulo();
    private static ServiceComentario serviceComentario = new ServiceComentario();
    private static ServiceValoracion serviceValoracion = new ServiceValoracion();
    private static ServiceUsuario serviceUsuario = new ServiceUsuario();

    public static Route paginaVerArticulo = (request, response) -> {
        Map<String, Object> map = new HashMap<>();
        Articulo articulo = serviceArticulo.encontrarPorId(Long.parseLong(request.queryParams("id")));

        String username = request.session().attribute("username");
        String password = request.session().attribute("password");

        Usuario usuario = serviceUsuario.existe(username, password);

        map.put("articulo", articulo);
        map.put("valoracionesPositivas", serviceArticulo.obtenerValoracionesPositivas(articulo).size());
        map.put("valoracionesNegativas", serviceArticulo.obtenerValoracionesNegativas(articulo).size());
        map.put("usuario", usuario);
        map.put("comentarios", serviceComentario.encontrarTodos());

        return new VistaUtil().render(map, Path.Template.VER_ARTICULO);
    };

    public static Route paginaNuevoArticulo = (request, response) -> {
        Map<String, Object> map = new HashMap<>();

        return new VistaUtil().render(map, Path.Template.NUEVO_ARTICULO);
    };
}
