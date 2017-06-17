package edu.pucmm.programacionweb2017.util;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;

/**
 * Created by gusta on 17-Jun-17.
 */
public class VistaUtil {
    private Configuration configuration;
    private FreeMarkerEngine freeMarkerEngine;

    public VistaUtil() {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates/");
        freeMarkerEngine = new FreeMarkerEngine(configuration);
    }

    public String render(Map<String, Object> map, String path) {
        return freeMarkerEngine.render(new ModelAndView(map, path));
    }
}
