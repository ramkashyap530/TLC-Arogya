
package notification;

import Dao.DBCon;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Notification;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 *
 * @author ramka
 */
public class NotificationDAO {
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt; 
    
    
    
    private static final Logger logger = Logger.getLogger(NotificationDAO.class.getName());

    private void ensureNotificationsTableExists(Connection conn) throws SQLException {
        String createTableSQL =
                "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='notifications' AND xtype='U') " +
                        "BEGIN " +
                        "CREATE TABLE notifications (" +
                        "    user_id VARCHAR(255) ," +
                        "    notification_id INT IDENTITY(1,1) PRIMARY KEY," +
                        "    type VARCHAR(50) ," +
                        "    source VARCHAR(255) ," +
                        "    code VARCHAR(255) ," +
                        "    message VARCHAR(1000) ," +
                        "    redirect_url VARCHAR(1000)," +
                        "    created_at DATETIME DEFAULT GETDATE()" +
                        ");" +
                        "END";
        try (PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
            stmt.execute();
        }
    }
    
    public boolean saveNotificationToDB(String userId, NotificationModel model) {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        try {
            conn = new DBCon().getConnection();

            // Ensure the notifications table exists
            ensureNotificationsTableExists(conn);

            String checkSQL = "SELECT COUNT(*) FROM notifications WHERE code = ? AND source = ?";
            checkStmt = conn.prepareStatement(checkSQL);
            checkStmt.setString(1, model.getCode());
            checkStmt.setString(2, model.getNotificationFrom());

            rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
              logger.log(Level.SEVERE, "Duplicate notification skipped for userId= {0}, code={1}, source={2}", new Object[]{userId, model.getCode(), model.getNotificationFrom()});
              return false;
            }

            String insertSQL = "INSERT INTO notifications (user_id, type, source, code, message, redirect_url, created_at) " +
                               "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";

            insertStmt = conn.prepareStatement(insertSQL);
            insertStmt.setString(1, userId);
            
            insertStmt.setString(2, model.getNotificationType());
            insertStmt.setString(3, model.getNotificationFrom());
            insertStmt.setString(4, model.getCode());
            insertStmt.setString(5, model.getNotificationMessage());
            insertStmt.setString(6, model.getRedirectUrl());

            int rowsInserted= insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                    logger.log(Level.INFO, "Notification inserted successfully for userId={0}, code={1}",
                               new Object[]{userId, model.getCode()});
                    return true;
                } else {
                    logger.log(Level.WARNING, "No rows inserted for notification for userId={0}", userId);
                    return false;
                }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception While Saving Notification", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Connect IO Exception Into NotificationDAO", e);
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { 
                logger.log(Level.WARNING,"Failed to close ResultSet", e);
            }
            try { if (checkStmt != null) checkStmt.close(); } catch (SQLException e) { 
                logger.log(Level.WARNING,"Failed to close checkStmt", e);
            }
            try { if (insertStmt != null) insertStmt.close(); } catch (SQLException e) {
               logger.log(Level.WARNING,"Failed to close insertStmt", e); 
            }
            try { if (conn != null) conn.close(); } catch (SQLException e) { 
               logger.log(Level.WARNING,"Failed to close DB connection", e); 
            }
        }
        return false;
    }
    
    
    
    
    //for showing the notificatrion on page 
    
public List<Notification_show> getTopNotifications() throws IOException {
        List<Notification_show> notifications = new ArrayList<>();
        
      
           con=new DBCon().getConnection();

           
          //for deleting the data from notification table older then 2 days 
          
          
       
         
         String query =
            "SELECT *,\n" +
"       DATEDIFF(SECOND, created_at, GETDATE()) AS seconds_ago,\n" +
"       CASE\n" +
"           WHEN DATEDIFF(SECOND, created_at, GETDATE()) < 60 THEN \n" +
"               CAST(DATEDIFF(SECOND, created_at, GETDATE()) AS VARCHAR) + ' seconds ago'\n" +
"           WHEN DATEDIFF(SECOND, created_at, GETDATE()) < 3600 THEN \n" +
"               CAST(DATEDIFF(SECOND, created_at, GETDATE()) / 60 AS VARCHAR) + ' minutes ago'\n" +
"           WHEN DATEDIFF(SECOND, created_at, GETDATE()) < 86400 THEN \n" +
"               CAST(DATEDIFF(SECOND, created_at, GETDATE()) / 3600 AS VARCHAR) + ' hours ago'\n" +
"           ELSE \n" +
"               CAST(DATEDIFF(SECOND, created_at, GETDATE()) / 86400 AS VARCHAR) + ' days ago'\n" +
"       END AS time_ago\n" +
"FROM notifications\n" +
"ORDER BY created_at DESC\n" +
"OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY;";

        try {
            ps = con.prepareStatement(query);
            
            System.out.println("------->"+query
            );
        
            rs = ps.executeQuery();

            while (rs.next()) {
                Notification_show n = new Notification_show();
                n.setUserId(rs.getString("user_id"));
                n.setNotificationId(rs.getInt("notification_id"));
                n.setType(rs.getString("type"));
                n.setSource(rs.getString("source"));
                n.setCode(rs.getString("code"));
                n.setMessage(rs.getString("message"));
                n.setRedirectUrl(rs.getString("redirect_url"));
                n.setCreatedAt(rs.getString("created_at"));
                n.setTimeAgo(rs.getString("time_ago"));
                notifications.add(n);
                notifications.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
            }
            
          
        } catch (Exception e) {
            System.out.println("---> Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception ex) {
                System.out.println("---> Cleanup error: " + ex.getMessage());
            }
        }

        return notifications;
    }  



public int total_noti(){
    int total=0;
    
    try{
       con=new DBCon().getConnection();
       
        String query ="select count(*)as total_noti from notifications";
        
        ps=con.prepareStatement(query);
        rs=ps.executeQuery();
        if(rs.next()){
            total=rs.getInt("total_noti");
        }
        
    }catch(Exception e){
        System.out.println("---->"+e);
        
    }
    
    
    
    return total;
}
    
        
         

}
