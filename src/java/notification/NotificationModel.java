package notification;



/**
 *
 * @author ramka
 */
public class NotificationModel {
   
    private int notificationId;
    private String code; // ADMIT_001 
    private String notificationType; // "Action" / "Non-Action"
    private String notificationMessage;
    private String notificationFrom; // e.g., "IPD Admit Patient"
    private String notificationCode; // e.g., PatientID or Unique Code
    private String redirectUrl;      // e.g., /patientDetails.jsp?id=101
    private boolean isRead;          // true = user has seen it
    private String role;             // e.g., "Admin"
    //private LocalDateTime createdAt;

    public NotificationModel() {
    }

    public NotificationModel(int notificationId, String code, String notificationType, String notificationMessage, String notificationFrom, String notificationCode, String redirectUrl, boolean isRead, String role) {
        this.notificationId = notificationId;
        this.code = code;
        this.notificationType = notificationType;
        this.notificationMessage = notificationMessage;
        this.notificationFrom = notificationFrom;
        this.notificationCode = notificationCode;
        this.redirectUrl = redirectUrl;
        this.isRead = isRead;
        this.role = role;
       // this.createdAt = createdAt;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getNotificationFrom() {
        return notificationFrom;
    }

    public void setNotificationFrom(String notificationFrom) {
        this.notificationFrom = notificationFrom;
    }

    public String getNotificationCode() {
        return notificationCode;
    }

    public void setNotificationCode(String notificationCode) {
        this.notificationCode = notificationCode;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }

    @Override
    public String toString() {
        return "NotificationModel{" + "notificationId=" + notificationId + ", code=" + code + ", notificationType=" + notificationType + ", notificationMessage=" + notificationMessage + ", notificationFrom=" + notificationFrom + ", notificationCode=" + notificationCode + ", redirectUrl=" + redirectUrl + ", isRead=" + isRead + ", role=" + role + '}';
    }

    

    
    
    
}
