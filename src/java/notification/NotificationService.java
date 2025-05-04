package notification;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramka
 */
public class NotificationService {
    
    private static final Logger logger = Logger.getLogger(NotificationService.class.getName());
    private final NotificationDAO notificationDAO;
    
        // Constructor
    public NotificationService() {
        this.notificationDAO = new NotificationDAO(); // Assuming NotificationDAO is responsible for DB operations
    }
    
     // sendNotification Method: Used to send and save notification to DB
    public void sendNotification(String userId, NotificationModel notification) {
        
        // Create a new NotificationModel object
        

        // Log notification details for debugging
        logger.log(Level.INFO, "Preparing to send notification: {0}", notification);

        // Save Notification to the database using NotificationDAO
        try {
            boolean isSaved = notificationDAO.saveNotificationToDB(userId,notification); // Assuming userId is for Admin
            if (isSaved) { 
                NotificationWebSocket.sendNotification(notification,userId);
                logger.log(Level.INFO, "Notification sent and saved successfully for userId={0}, code={1}",
                           new Object[]{userId, notification.getCode()});
            } else {
                logger.log(Level.WARNING, "Failed to save notification for userId={0}, code={1}", 
                           new Object[]{userId, notification.getCode()});
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while sending notification", e);
        }
    }
    
}
