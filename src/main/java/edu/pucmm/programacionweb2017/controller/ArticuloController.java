package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.entity.Articulo;
import edu.pucmm.programacionweb2017.entity.Etiqueta;
import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.service.ServiceArticulo;
import edu.pucmm.programacionweb2017.service.ServiceComentario;
import edu.pucmm.programacionweb2017.service.ServiceEtiqueta;
import edu.pucmm.programacionweb2017.service.ServiceUsuario;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.VistaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import java.util.*;

public class ArticuloController {
    private static final Logger logger = LoggerFactory.getLogger(ArticuloController.class);

    private static ServiceArticulo serviceArticulo = new ServiceArticulo();
    public static Route eliminarArticulo = (request, response) -> {
        Articulo articulo = serviceArticulo.encontrarPorId(Long.parseLong(request.queryParams("id")));

        serviceArticulo.borrar(articulo);

        response.redirect(Path.Web.INICIO);

        return null;
    };
    private static ServiceComentario serviceComentario = new ServiceComentario();
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
        Usuario usuario = serviceUsuario.encontrarPorNombreUsuario(request.session().attribute("username"));

        map.put("usuario", usuario);

        return new VistaUtil().render(map, Path.Template.NUEVO_ARTICULO);
    };
    public static Route paginaModificarArticulo = (request, response) -> {
        Map<String, Object> map = new HashMap<>();
        Articulo articulo = serviceArticulo.encontrarPorId(Long.parseLong(request.queryParams("id")));
        Usuario usuario = serviceUsuario.encontrarPorNombreUsuario(request.session().attribute("username"));

        String etiquetas = "";

        articulo.getListaEtiquetas().forEach(etiqueta -> {
            etiquetas.concat(etiqueta + ",");
        });

        StringBuilder stringBuilder = new StringBuilder(etiquetas);

        if (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length()) == ',')
            stringBuilder.deleteCharAt(stringBuilder.length());

        map.put("usuario", usuario);
        map.put("articulo", articulo);
        map.put("titulo", articulo.getTitulo());
        map.put("cuerpo", articulo.getCuerpo());
        map.put("etiquetas", stringBuilder.toString());

        return new VistaUtil().render(map, Path.Template.MODIFICAR_ARTICULO);
    };
    private static ServiceEtiqueta serviceEtiqueta = new ServiceEtiqueta();
    public static Route crearNuevoArticulo = (request, response) -> {
        Map<String, Object> map = new HashMap<>();
        Set<Etiqueta> etiquetas = new HashSet<>();

        for (String string : Arrays.asList(request.queryMap().get("Eti").value().split(","))) {
            if (string != "") {
                Etiqueta etiqueta = serviceEtiqueta.encontrarPorEtiqueta(string);
                if (etiqueta != null) {
                    etiquetas.add(etiqueta);
                } else {
                    etiquetas.add(new Etiqueta(string));
                }
            }
        }

        String titulo = request.queryParams("Titu");
        String cuerpo = request.queryParams("Cuer");
        Usuario usuario = serviceUsuario.encontrarPorId(Long.parseLong(request.queryParams("autor")));
        Date fecha = new Date();

        Articulo articulo;

        if (etiquetas.isEmpty()) {
            articulo = new Articulo(titulo, cuerpo, usuario, fecha, null, null, null);
        } else {
            articulo = new Articulo(titulo, cuerpo, usuario, fecha, null, etiquetas, null);
        }

        serviceArticulo.insertar(articulo);

        response.redirect(Path.Web.INICIO);

        return new VistaUtil().render(map, Path.Template.INICIO);
    };
    public static Route moficarArticulo = (request, response) -> {
        Set<Etiqueta> etiquetas = new HashSet<>();
        Articulo articulo = serviceArticulo.encontrarPorId(Long.parseLong(request.queryParams("id")));

        for (String string : Arrays.asList(request.queryMap().get("Eti").value().split(","))) {
            Etiqueta etiqueta = serviceEtiqueta.encontrarPorEtiqueta(string);
            if (etiqueta != null) {
                etiquetas.add(etiqueta);
            } else {
                etiquetas.add(new Etiqueta(string));
            }
        }

        String titulo = request.queryParams("Titu");
        String cuerpo = request.queryParams("Cuer");
        Usuario usuario = articulo.getAutor();

        articulo.setTitulo(titulo);
        articulo.setCuerpo(cuerpo);
        articulo.setAutor(usuario);
        articulo.setFecha(new Date());
        articulo.setListaEtiquetas(etiquetas);

        serviceArticulo.actualizar(articulo);

        response.redirect(Path.Web.INICIO);
        return null;
    };
}
