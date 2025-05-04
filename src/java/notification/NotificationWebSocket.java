
package notification;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws/notifications/{userId}")
public class NotificationWebSocket {
    private static final Map<String, Session> adminUserSessions = new ConcurrentHashMap<>();
    private static final Logger logger = Logger.getLogger(NotificationWebSocket.class.getName());

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        adminUserSessions.put(userId, session);
        logger.info("Admin connected: " + userId);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        adminUserSessions.remove(userId);
        logger.info("Admin disconnected: " + userId);
    }

    public static void sendNotification(NotificationModel notification, String senderUserId) {
        try {
            String json = new com.google.gson.Gson().toJson(notification);
            for (Map.Entry<String, Session> entry : adminUserSessions.entrySet()) {
                String userId = entry.getKey();
                Session session = entry.getValue();
                if (!userId.equals(senderUserId)) { // don't send to sender
                    try {
                        session.getBasicRemote().sendText(json);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "WebSocket send error", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to send notification", e);
        }
    }
}
