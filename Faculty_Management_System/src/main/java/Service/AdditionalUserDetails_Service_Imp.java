package Service;

import java.io.File;
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

import DAO.AdditionalUserDetails_DAO;
import DAO.Notification_DAO;
import Model.AdditionalUserDetails;
import Model.Document;
import Model.Notification;
import Upload.File_Upload;

@Service
@Transactional
public class AdditionalUserDetails_Service_Imp implements AdditionalUserDetails_Service {

	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	Notification_DAO notification_dao;
	
	@Autowired
	AdditionalUserDetails_DAO additionaluserdetails_dao;
	
	@Override
	public AdditionalUserDetails findByAdditionalUserDetails(String additional_user_details) {
		return additionaluserdetails_dao.findByAdditionalUserDetails(additional_user_details);
	}

	@Override
	public void addAdditionalUserDetails(AdditionalUserDetails additional_user_details,MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		AdditionalUserDetails ad = additionaluserdetails_dao.findById(additional_user_details.getAdditionaluserdetails_id());
				String u=UUID.randomUUID().toString();
				String uploadDir = uploadDirectory + "Images/";
				Document document=new Document();
				if(fileName.length()>0) {
					document.setFileName(fileName);
					document.setFileType(multipartFile.getContentType());
					document.setSize(multipartFile.getSize());
					document.setNewFileName(u+fileName);
					document.setFileDownloadUri(uploadDir+u+fileName);
					additional_user_details.setPhoto(document);
					System.out.println(uploadDir);
					File_Upload.saveFile(uploadDir, u+fileName, multipartFile);
					}
				else {
					additional_user_details.setPhoto(ad.getPhoto());
				}
				additionaluserdetails_dao.addAdditionalUserDetails(additional_user_details);
	}

	@Override
	public void deleteAdditionalUserDetails(AdditionalUserDetails additional_user_details) {
		additionaluserdetails_dao.deleteAdditionalUserDetails(additional_user_details);
	}

	@Override
	public List<AdditionalUserDetails> getAdditionalUserDetailsList() {
		return additionaluserdetails_dao.getAdditionalUserDetailsList();
	}
	
	@Override
	public Document findByDocumentName(String fileName) {
		return notification_dao.findByDocumentName(fileName);
	}
	
	@Override
	public void updateAdditionalUserDetails(AdditionalUserDetails additional_user_details,MultipartFile multipartFile) throws IOException {
String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
AdditionalUserDetails ad = additionaluserdetails_dao.findById(additional_user_details.getAdditionaluserdetails_id());
		String u=UUID.randomUUID().toString();
		String uploadDir = uploadDirectory + "Images/";
		System.out.println(additional_user_details.getPhoto());
		System.out.println(fileName);
		if(fileName.length()>0) {
			Document document=new Document();
			document.setFileName(fileName);
			document.setFileType(multipartFile.getContentType());
			document.setSize(multipartFile.getSize());
			document.setNewFileName(u+fileName);
			document.setFileDownloadUri(uploadDir+u+fileName);
			
			try {
				File file = new File(ad.getPhoto().getFileDownloadUri());
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			additional_user_details.setPhoto(document);
			System.out.println(uploadDir);
			File_Upload.saveFile(uploadDir, u+fileName, multipartFile);
			}
		else {
			additional_user_details.setPhoto(ad.getPhoto());
		}
		
		additionaluserdetails_dao.updateAdditionalUserDetails(additional_user_details);
		
	}

	@Override
	public AdditionalUserDetails findAdditionalUserDetailsById(long additionaluserdetails_id) {
		return additionaluserdetails_dao.findById(additionaluserdetails_id);
	}

}
