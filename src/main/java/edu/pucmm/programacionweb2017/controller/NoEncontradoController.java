package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.VistaUtil;
import spark.Route;

public class NoEncontradoController {
    public static Route paginaNoEncontrada = (request, response) -> {
        return new VistaUtil().render(null, Path.Template.NO_ENCONTRADO);
    };
}
