package edu.pucmm.programacionweb2017.util;

import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;

public class VistaUtil {
    private static final Logger logger = LoggerFactory.getLogger(VistaUtil.class);

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
