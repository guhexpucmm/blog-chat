package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.service.ServiceArticulo;
import edu.pucmm.programacionweb2017.service.ServiceUsuario;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.VistaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class InicioController {
    private static final Logger logger = LoggerFactory.getLogger(InicioController.class);

    private static ServiceArticulo serviceArticulo = new ServiceArticulo();
    private static ServiceUsuario serviceUsuario = new ServiceUsuario();

    public static Route paginaInicio = (request, response) -> {
        Map<String, Object> mapa = new HashMap<>();

        String username = request.session().attribute("username");
        String password = request.session().attribute("password");

        double totalArticulos = serviceArticulo.totalArticulos();
        double totalPaginas = totalArticulos / 5;
        totalPaginas = Math.ceil(totalPaginas);

        Usuario usuario = serviceUsuario.existe(username, password);

        mapa.put("usuario", usuario);
        mapa.put("articulos", serviceArticulo.encontrarTodos());
        mapa.put("paginas", totalPaginas);

        return new VistaUtil().render(mapa, Path.Template.INICIO);
    };
}
