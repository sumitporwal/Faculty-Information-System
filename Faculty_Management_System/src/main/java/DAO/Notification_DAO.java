package DAO;

import java.util.List;

import Model.Document;
import Model.Notification;

public interface Notification_DAO {
	public Notification findByNotification(String notification);

	public void addNotification(Notification notification);

	public void deleteNotification(Notification notification);

	public List<Notification> getNotificationList();

	public Document findByDocumentName(String fileName);

	public void updateNotification(Notification notification);

	public Notification findById(long notification_id);

	public List<Notification> getNotificationListByVisibility(String role);
}
