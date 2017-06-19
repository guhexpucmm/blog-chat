package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.entity.Articulo;
import edu.pucmm.programacionweb2017.service.ServiceArticulo;
import edu.pucmm.programacionweb2017.service.ServiceEtiqueta;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.VistaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtiquetaController {
    private static final Logger logger = LoggerFactory.getLogger(EtiquetaController.class);
    private static final ServiceArticulo serviceArticulo = new ServiceArticulo();
    private static final ServiceEtiqueta serviceEtiqueta = new ServiceEtiqueta();

    public static Route paginaVerEtiqueta = (request, response) -> {
        Map<String, Object> map = new HashMap<>();

        List<Articulo> list = serviceArticulo.obtenerArticulosDadaUnaEtiqueta(serviceEtiqueta.encontrarPorId(Long.valueOf(request.queryParams("id"))));

        map.put("articulos", list);

        return new VistaUtil().render(map, Path.Template.VER_ETIQUETA);
    };
}
