package edu.pucmm.programacionweb2017.websocket;


import com.google.gson.Gson;
import edu.pucmm.programacionweb2017.entity.MensajeChat;
import edu.pucmm.programacionweb2017.main.Main;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;



/**
 * Representa la clase para enviar información desde el servidor al cliente.
 * Created by vacax on 06/06/16.
 */
@WebSocket
public class ServidorMensajesWebSocketHandler {

    /**
     * Una vez conectado el cliente se activa este metodo.
     * @param usuario
     */
    @OnWebSocketConnect
    public void conectando(Session usuario){
        System.out.println("Conectando Usuario: "+usuario.getLocalAddress().getAddress().toString());

    }

    /**
     * Una vez cerrado la conexión, es ejecutado el metodo anotado
     * @param usuario
     * @param statusCode
     * @param reason
     */
    @OnWebSocketClose
    public void cerrandoConexion(Session usuario, int statusCode, String reason) {
        System.out.println("Desconectando el usuario: "+usuario.getLocalAddress().getAddress().toString());
        for(Iterator<Map.Entry<String, Session>> it = Main.usuariosConectados.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Session> entry = it.next();
            if(entry.getValue().equals(usuario)) {
                it.remove();
            }
        }
    }

    /**
     * Una vez recibido un mensaje es llamado el metodo anotado.
     * @param usuario
     * @param message
     */
    @OnWebSocketMessage
    public void recibiendoMensaje(Session usuario, String message) throws IOException {
        System.out.println("Recibiendo del cliente: "+usuario.getLocalAddress().getAddress().toString()+" - Mensaje"+message);
        Gson gson = new Gson();
        MensajeChat mc = gson.fromJson(message, MensajeChat.class);

        if (mc.isInicializando()){
            Main.usuariosConectados.put(mc.getUsuario_origen(),usuario);
            return;
        }
        Session session = Main.usuariosConectados.get(mc.getUsuario_destino());
        if( session != null)
            session.getRemote().sendString(gson.toJson(mc));
        else{
             mc = new MensajeChat("servidor","El usuario se ha desconectado","",false,"Servidor");
            usuario.getRemote().sendString(gson.toJson(mc));
        }


//        for (Map.Entry<String, Session> entry : Main.usuariosConectados.entrySet())
//        {
//            if(mc.getUsuario_destino().equals(entry.getKey())){
//                System.out.println(entry.getKey());
//                entry.getValue().getRemote().sendString(gson.toJson(mc));
//            }
//
//        }

    }



}
