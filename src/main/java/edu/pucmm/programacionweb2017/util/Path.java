package edu.pucmm.programacionweb2017.util;

/**
 * Created by gusta on 17-Jun-17.
 */
public class Path {

    public static class Web {
        //Ruta inicio por defecto
        public static String INICIO = "/inicio";

        //Inicio sesion
        public static String INICIAR_SESION = "/inicioSesion";
        public static String CERRAR_SESION = "/cerrarSesion";

        //Articulos
        public static String VER_ARTICULO = "/articulo/";
        public static String NUEVO_ARTICULO = "/articulo/nuevo";

        //Usuarios
        public static String CREAR_USUARIO = "/crearUsuario/";

        //Etiquetas
        public static String ETIQUETA = "/etiqueta/";

        //AUTH STATUS
        public static String AUTH_STATUS = "AUTH_STATUS";
    }

    public static class Template {
        public static String INICIO = "inicio.ftl";

        public static String VER_ARTICULO = "articulo/ver.ftl";
        public static String NUEVO_ARTICULO = "articulo/nuevo.ftl";
    }
}
