package edu.pucmm.programacionweb2017.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gusta on 17-Jun-17.
 */
public class Path {
    private static final Logger logger = LoggerFactory.getLogger(Path.class);

    public static class Web {
        //Ruta inicio por defecto
        public static String INICIO = "/inicio";

        //Inicio sesion
        public static String INICIAR_SESION = "/inicioSesion";
        public static String CERRAR_SESION = "/cerrarSesion";

        //Articulos
        public static String VER_ARTICULO = "/articulo/";
        public static String NUEVO_ARTICULO = "/articulo/nuevo/";
        public static String ELIMINAR_ARTICULO = "/articulo/eliminar/";
        public static String MODIFICAR_ARTICULO = "/articulo/modificar/";

        //Valoracion
        public static String VALORACION = "/valoracion/";

        //Usuarios
        public static String NUEVO_USUARIO = "/usuario/nuevo/";

        //Etiquetas
        public static String ETIQUETA = "/etiqueta/";

        //AUTH STATUS
        public static String AUTH_STATUS = "AUTH_STATUS";

        //No encontrado
        public static String NO_TIENE_PERMISOS = "/error";

        //No tiene permisos
    }

    public static class Template {
        public static String INICIO = "inicio.ftl";

        public static String VER_ARTICULO = "articulo/ver.ftl";
        public static String NUEVO_ARTICULO = "articulo/nuevo.ftl";
        public static String MODIFICAR_ARTICULO = "articulo/modificar.ftl";

        public static String NUEVO_USUARIO = "usuario/nuevo.ftl";

        public static String NO_TIENE_PERMISOS = "noTienePermiso.ftl";
    }
}
