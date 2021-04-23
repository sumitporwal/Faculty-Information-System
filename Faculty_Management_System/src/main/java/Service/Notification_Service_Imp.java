package Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import DAO.Notification_DAO;
import Model.Document;
import Model.Notification;
import Model.User;
import Upload.File_Upload;

@Service
@Transactional
public class Notification_Service_Imp implements Notification_Service {

	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	Notification_DAO notification_dao;
	
	@Override
	public Notification findByNotification(String notification) {
		return notification_dao.findByNotification(notification);
	}

	@Override
	public void addNotification(Notification notification,MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		Date date=Calendar.getInstance().getTime();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String generationDate=dateFormat.format(date);
		System.out.println(date);
		System.out.println(generationDate);
		
		String u=UUID.randomUUID().toString();
		String uploadDir = uploadDirectory + "Notifications/";
		
		Document document=new Document();
		document.setFileName(fileName);
		document.setFileType(multipartFile.getContentType());
		document.setSize(multipartFile.getSize());
		document.setNewFileName(u+fileName);
		document.setFileDownloadUri(uploadDir+u+fileName);
		
		notification.setDocument(document);
		
		notification.setGeneration_date(generationDate);
		notification.setLast_modified_date(generationDate);
		notification.setStatus("active");
		notification_dao.addNotification(notification);
		
		System.out.println(uploadDir);
		File_Upload.saveFile(uploadDir, u+fileName, multipartFile);
	}

	@Override
	public void deleteNotification(Notification notification) {
		notification_dao.deleteNotification(notification);
	}

	@Override
	public List<Notification> getNotificationList() {
		return notification_dao.getNotificationList();
	}
	
	@Override
	public Document findByDocumentName(String fileName) {
		return notification_dao.findByDocumentName(fileName);
	}
	
	@Override
	public void updateNotification(Notification notification) {
		Date date=Calendar.getInstance().getTime();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String CurrentDate=dateFormat.format(date);
		
		notification.setLast_modified_date(CurrentDate);
		notification_dao.updateNotification(notification);
	}

	@Override
	public Notification findNotificationById(long notification_id) {
		return notification_dao.findById(notification_id);
	}
	
	@Override
	public List<Notification> getNotificationListByVisibility(String role) {
		return notification_dao.getNotificationListByVisibility(role);
	}

}
