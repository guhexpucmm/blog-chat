package edu.pucmm.programacionweb2017.controller;

import edu.pucmm.programacionweb2017.main.Main;
import edu.pucmm.programacionweb2017.service.ServiceUsuario;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import static edu.pucmm.programacionweb2017.main.Main.broadcastMessage;
import static edu.pucmm.programacionweb2017.main.Main.userUsernameMap;

@WebSocket
public class WebSocketController {
    private String sender, msg;
    private ServiceUsuario serviceUsuario = new ServiceUsuario();

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        String username = "Usuario" + Main.nextUserNumber++;
        userUsernameMap.put(user, username);
        broadcastMessage(sender = "Servidor", msg = (username + " se unio al chat."));
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        String username = userUsernameMap.get(user);
        userUsernameMap.remove(user);
        broadcastMessage(sender = "Servidor", msg = (username + " se fue del chat.                                                                                                                                                                                                                                                                                                  "));
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        broadcastMessage(sender = userUsernameMap.get(user), msg = message);
    }
}