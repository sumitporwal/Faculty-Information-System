package Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Model.Document;
import Model.Notification;

public interface Notification_Service {

	  public Notification findByNotification(String notification);
	  public void addNotification(Notification notification,MultipartFile multipartFile) throws IOException;
	  public void deleteNotification(Notification notification);
	  public List<Notification> getNotificationList();
	  public Document findByDocumentName(String fileName);
	  public void updateNotification(Notification notification);
	  public Notification findNotificationById(long notification_id);
	  public List<Notification> getNotificationListByVisibility(String role);
}
