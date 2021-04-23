package DAO;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Model.Document;
import Model.Notification;

@Repository
public class Notification_DAO_Imp implements Notification_DAO {

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public Notification findByNotification(String notification) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Notification> query = currentSession.createQuery("from Notification where subject=:subject",
				Notification.class);
		query.setParameter("subject", notification);
		List<Notification> list = query.getResultList();
		Notification notification1 = new Notification();
		if (list.size() > 0) {
			notification1 = list.get(0);
		}
		return notification1;
	}

	@Override
	public void addNotification(Notification notification) {

		sessionfactory.getCurrentSession().save(notification);

	}

	@Override
	public void deleteNotification(Notification notification) {
		try {
			File file = new File(notification.getDocument().getFileDownloadUri());
			file.delete();
			sessionfactory.getCurrentSession().delete(notification);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Notification> getNotificationList() {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Notification> query = currentSession.createQuery("from Notification", Notification.class);
		List<Notification> list = query.getResultList();
		return list;
	}

	@Override
	public Document findByDocumentName(String fileName) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Document> query = currentSession.createQuery("from Document where newFileName=:fileName", Document.class);
		query.setParameter("fileName", fileName);
		List<Document> list = query.getResultList();
		Document document = new Document();
		if (list.size() > 0) {
			document = list.get(0);
		}
		return document;
	}

	@Override
	public void updateNotification(Notification notification) {
		try {
			Session currentSession = sessionfactory.getCurrentSession();
			Notification n = findById(notification.getNotification_id());
			n.setSubject(notification.getSubject());
			n.setContent(notification.getContent());
			n.setGeneration_date(n.getGeneration_date());
			n.setDue_date(notification.getDue_date());
			n.setLast_modified_date(notification.getLast_modified_date());
			n.setStatus(notification.getStatus());
			n.setVisibility(notification.getVisibility());
			n.setDocument(n.getDocument());
			n.setUser(n.getUser());
			currentSession.update(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Notification findById(long notification_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Notification> query = currentSession
				.createQuery("from Notification where notification_id=:notification_id", Notification.class);
		query.setParameter("notification_id", notification_id);
		List<Notification> list = query.getResultList();
		System.out.println(list);
		Notification notification1 = new Notification();
		if (list.size() > 0) {
			notification1 = list.get(0);
		}
		return notification1;
	}

	@SuppressWarnings("null")
	@Override
	public List<Notification> getNotificationListByVisibility(String role) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Notification> query = currentSession.createQuery("from Notification", Notification.class);
		List<Notification> list = query.getResultList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int k = 0;
				String[] visibility = list.get(i).getVisibility().split(",");
				for (int j = 0; j < visibility.length; j++) {
					if (visibility[j].equals(role)) {
						k = 1;
					}
				}
				if (k == 0) {
					list.remove(i);
					i--;
				}
			}
		}
		return list;
	}

}
