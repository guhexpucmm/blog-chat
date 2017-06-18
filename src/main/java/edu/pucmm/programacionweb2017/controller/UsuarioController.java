package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.service.ServiceUsuario;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.VistaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    public static Route paginaNuevoUsuario = (request, response) -> {
        Map<String, Object> map = new HashMap<>();

        return new VistaUtil().render(map, Path.Template.NUEVO_USUARIO);
    };
    private static ServiceUsuario serviceUsuario = new ServiceUsuario();
    public static Route crearNuevoUsuario = (request, response) -> {
        Map<String, Object> map = new HashMap<>();

        String username = request.queryParams("User");
        String nombre = request.queryParams("Nom");
        String password = request.queryParams("Pass");
        boolean administrador = Boolean.parseBoolean(request.queryParams("Adm"));
        boolean autor = Boolean.parseBoolean(request.queryParams("Aut"));

        Usuario usuario = new Usuario(username, nombre, password, administrador, autor);

        serviceUsuario.insertar(usuario);

        response.redirect(Path.Web.INICIO);

        response.redirect(Path.Web.INICIO);
        return null;
    };
}
