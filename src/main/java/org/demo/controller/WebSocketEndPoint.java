package org.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/socket")
@Component
public class WebSocketEndPoint {
    private final Logger log = LoggerFactory.getLogger(WebSocketEndPoint.class);
    private static CopyOnWriteArraySet<WebSocketEndPoint> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session = null;

    private int count = 0;

    @OnOpen
    public void onOpen(Session session){
        System.out.println("Session " + session.getId() + " has opened a connection");
        try {
            this.session=session;
            webSocketSet.add(this);
//            count++;
            log.info("Connection Established and the number of current connections is "+ webSocketSet.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Get a message from client: "+ message.toString());
        try {
            log.info("reply msg: " + message);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() throws Exception {
        try {
            webSocketSet.remove(this); // remove the session from the set;
//            count--;
            System.out.println("Session " + session.getId() + " has closed and the number of current connections is "+ webSocketSet.size());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

//    @OnError
//    public void onError(Session session) throws Exception {
//        if(session.isOpen()) {
//            log.info("Session " +session.getId()+" has an error and has closed!");
//            webSocketSet.remove(this);
//        }
//    }

    //send message to all clients
    public synchronized void sendMessage(String message) throws IOException {
        for(WebSocketEndPoint wse: webSocketSet){
            try {
                wse.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
