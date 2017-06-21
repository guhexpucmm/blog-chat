package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.VistaUtil;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class SalaChatController {
    public static Route paginaSalaChat = (request, response) -> {
        Map<String, Object> map = new HashMap<>();

        return new VistaUtil().render(map, Path.Template.SALA_CHAT);
    };
}
