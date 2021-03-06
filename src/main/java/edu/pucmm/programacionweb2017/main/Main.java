package edu.pucmm.programacionweb2017.main;

import edu.pucmm.programacionweb2017.controller.*;
import edu.pucmm.programacionweb2017.database.BootstrapServices;
import edu.pucmm.programacionweb2017.entity.Usuario;
import edu.pucmm.programacionweb2017.service.ServiceArticulo;
import edu.pucmm.programacionweb2017.service.ServiceUsuario;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.websocket.ServidorMensajesWebSocketHandler;
import freemarker.template.Configuration;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    public static final ServiceArticulo serviceArticulo = new ServiceArticulo();
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    // this map is shared between sessions and threads, so it needs to be thread-safe (http://stackoverflow.com/a/2688817)
    public static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    public static int nextUserNumber = 1; //Assign to username for next connecting user
    public static Map<String, Session> usuariosConectados = new HashMap<>();

    private Main() {
        logger.info("Iniciando aplicacion web.");
        enableDebugScreen();
        setConfiguraciones();
        setServidor();
        setWebSocket();
        setRedireccion();
        setRutas();
        setUsuario();
    }

    public static void main(String[] args) {
        webSocket("/mensajeServidor", ServidorMensajesWebSocketHandler.class);
        new Main();



        //CHAT
        ServiceUsuario serviceUsuario = new ServiceUsuario();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates/");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        get("/administradoresConectados", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = new Usuario();
            u.setAutor(false);
            u.setAdministrador(false);
            System.out.println(request.queryParams("nombre"));
            u.setUsername(request.queryParams("nombre"));
            u.setEsInvitado(true);

            attributes.put("administradoresautores",UsuarioController.buscarAdminsAutores());

            attributes.put("usuario",u);
            return new ModelAndView(attributes, "/chat/showAdmins.ftl");
        }, freeMarkerEngine);

        get("/chatRoom/:admin/:nombre", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = request.session().attribute("usuario");
            if(u==null){
                u = new Usuario();
                u.setAutor(false);
                u.setAdministrador(false);
                u.setUsername(request.params("nombre"));
                u.setEsInvitado(true);
            }
            Usuario a = serviceUsuario.encontrarPorNombreUsuario(request.params("admin"));
            //Usuario a = UsuarioServices.getInstancia().find(request.params("admin"));
            if(a==null){
                response.redirect("/");
            }
            attributes.put("administrador",a);
            attributes.put("usuario",u);
            return new ModelAndView(attributes, "/chat/chatUser.ftl");
        }, freeMarkerEngine);

        get("/chatRoom", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String us = request.session().attribute("username");
           //String usuario = request.queryParams("nombreUsuario");
            Usuario u = serviceUsuario.encontrarPorNombreUsuario(us);
           // System.out.println(u.getNombre());
            if(u==null){
                u = new Usuario();
                u.setAutor(false);
                u.setAdministrador(false);
                u.setUsername("invitado");
                u.setEsInvitado(true);
            }
            attributes.put("usuario",u);
            return new ModelAndView(attributes, "/chat/admin-autorChatroom.ftl");
        }, freeMarkerEngine);

        get("/chatRoom/autor", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = new Usuario();
            u.setAutor(false);
            u.setAdministrador(false);
            System.out.println(request.queryParams("nombre"));
            u.setUsername(request.queryParams("nombre"));
            u.setEsInvitado(true);

            Usuario a = serviceUsuario.encontrarPorNombreUsuario(request.params("autor"));
            if(a==null){
                response.redirect("/");
            }

            attributes.put("usuario",u);
            attributes.put("administrador",a);
            return new ModelAndView(attributes, "chatUser.ftl");
        }, freeMarkerEngine);

        get("/articulos/:pagina", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            int inicio = (Integer.parseInt(request.params("pagina")) * 5) - 5;
            int fin = inicio + 5;

            attributes.put("articulos", serviceArticulo.obtenerArticulosPaginacion(inicio, fin));

            return new ModelAndView(attributes, "articulos.ftl");
        }, freeMarkerEngine);
    }

    private static void setUsuario() {
        logger.info("Configurando usuario por defecto.");
        Usuario usuario = new Usuario(
                "admin",
                "Administrador",
                "admin",
                true,
                true
        );

        ServiceUsuario serviceUsuario = new ServiceUsuario();

        if (serviceUsuario.encontrarPorNombreUsuario(usuario.getUsername()) == null)
            serviceUsuario.insertar(usuario);
    }

    private static void setWebSocket() {
        logger.info("Inicializando websocket...");
        webSocket(Path.Web.WEBSOCKET_CHAT, WebSocketController.class);
    }

    //Sends a message from one user to all users, along with a list of current usernames
    public static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlMessageFromSender(sender, message))
                        .put("userlist", userUsernameMap.values())
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //Builds a HTML element with a sender-name, a message, and a timestamp,
    private static String createHtmlMessageFromSender(String sender, String message) {
        return article(
                b("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + sender + " dice:"),
                p(message)).render();
    }

    private void setConfiguraciones() {
        logger.info("Inicializando configuracion.");
        logger.info("Configurando carpeta de recursos publica.");
        staticFileLocation("/public");
    }

    private void setServidor() {
        logger.info("Iniciando el servidor h2.");
        BootstrapServices.iniciarServer();
        logger.info("Servidor inicializado.");
    }

    private void setRedireccion() {
        logger.info("Configurando redireccion");
        get("/", (request, response) -> {
            response.redirect(Path.Web.INICIO);
            return null;
        });

        get("", (request, response) -> {
            response.redirect(Path.Web.INICIO);
            return null;
        });
    }

    private void setRutas() {
        logger.info("Mapeando rutas...");
        get("/", InicioController.paginaInicio);
        get(Path.Web.INICIO, InicioController.paginaInicio);
        post(Path.Web.INICIAR_SESION, SesionController.iniciarSesion);
        post(Path.Web.CERRAR_SESION, SesionController.cerrarSesion);

        get(Path.Web.VER_ARTICULO, ArticuloController.paginaVerArticulo);

        before(Path.Web.NUEVO_ARTICULO, FiltroController.filtro);
        get(Path.Web.NUEVO_ARTICULO, ArticuloController.paginaNuevoArticulo);
        post(Path.Web.NUEVO_ARTICULO, ArticuloController.crearNuevoArticulo);
        before(Path.Web.ELIMINAR_ARTICULO, FiltroController.filtro);
        get(Path.Web.ELIMINAR_ARTICULO, ArticuloController.eliminarArticulo);
        before(Path.Web.MODIFICAR_ARTICULO, FiltroController.filtro);
        get(Path.Web.MODIFICAR_ARTICULO, ArticuloController.paginaModificarArticulo);
        post(Path.Web.MODIFICAR_ARTICULO, ArticuloController.moficarArticulo);

        before(Path.Web.NUEVO_COMENTARIO, FiltroController.filtro);
        post(Path.Web.NUEVO_COMENTARIO, ComentarioController.crearComentario);
        before(Path.Web.BORRAR_COMENTARIO, FiltroController.filtro);
        get(Path.Web.BORRAR_COMENTARIO, ComentarioController.borrarComentario);

        get(Path.Web.ETIQUETA, EtiquetaController.paginaVerEtiqueta);

        get(Path.Web.VALORACION, ValoracionController.valoracion);

        before(Path.Web.SALA_CHAT, FiltroController.filtro);
        get(Path.Web.SALA_CHAT, SalaChatController.paginaSalaChat);

        before(Path.Web.NUEVO_USUARIO, FiltroController.filtro);
        get(Path.Web.NUEVO_USUARIO, UsuarioController.paginaNuevoUsuario);
        post(Path.Web.NUEVO_USUARIO, UsuarioController.crearNuevoUsuario);

        get(Path.Web.NO_TIENE_PERMISOS, NoTienePermisosController.paginaNoTienePermisos);
        get("/*/", NoEncontradoController.paginaNoEncontrada);
    }

    private String renderContent(String htmlFile) throws URISyntaxException, IOException {
        return new String(Files.readAllBytes(Paths.get(getClass().getResource(htmlFile).toURI())), StandardCharsets.UTF_8);
    }
}