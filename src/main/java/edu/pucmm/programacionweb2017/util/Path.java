package edu.pucmm.programacionweb2017.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Path {
    private static final Logger logger = LoggerFactory.getLogger(Path.class);

    public static class Web {
        //Ruta inicio por defecto
        public static String INICIO = "/inicio/";

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
        public static String MODIFICAR_USUARIO = "/usuario/modificar/";
        public static String ELIMINAR_USUARIO = "/usuario/eliminar";
        public static String VER_USUARIO = "/usuario/";

        //Etiquetas
        public static String ETIQUETA = "/etiqueta/";
        public static String NUEVA_ETIQUETA = "/etiqueta/nuevo";
        public static String MODIFICAR_ETIQUETA = "/etiqueta/modificar/";
        public static String ELIMINAR_ETIQUETA = "/etiqueta/eliminar/";
        public static String VER_ARTICULOS_POR_ETIQUETAS = "/etiqueta/articulos/";

        public static String NUEVO_COMENTARIO = "/comentario/nuevo/";
        public static String BORRAR_COMENTARIO = "/comentario/borrar/";

        //AUTH STATUS
        public static String AUTH_STATUS = "AUTH_STATUS";

        //No encontrado
        public static String NO_TIENE_PERMISOS = "/error";

        //Chat
        public static String WEBSOCKET_CHAT = "/chat";
        public static String SALA_CHAT = "/salaChat";
    }

    public static class Template {
        public static String INICIO = "inicio.ftl";

        public static String VER_ARTICULO = "articulo/ver.ftl";
        public static String NUEVO_ARTICULO = "articulo/nuevo.ftl";
        public static String MODIFICAR_ARTICULO = "articulo/modificar.ftl";

        public static String NUEVO_USUARIO = "usuario/nuevo.ftl";

        public static String VER_ETIQUETA = "etiqueta/ver.ftl";

        public static String SALA_CHAT = "salaChat.ftl";

        public static String NO_TIENE_PERMISOS = "noTienePermiso.ftl";
        public static String NO_ENCONTRADO = "noEncontrado.ftl";
    }
}
