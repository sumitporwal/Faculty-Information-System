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

import DAO.Vacancy_DAO;
import Model.Document;
import Model.Vacancy;
import Upload.File_Upload;

@Service
@Transactional
public class Vacancy_Service_Imp implements Vacancy_Service {

	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	Vacancy_DAO vacancy_dao;
	
	@Override
	public Vacancy findByVacancy(String vacancy) {
		return vacancy_dao.findByVacancy(vacancy);
	}

	@Override
	public void addVacancy(Vacancy vacancy, MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		Date date=Calendar.getInstance().getTime();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String generationDate=dateFormat.format(date);
		System.out.println(date);
		System.out.println(generationDate);
		
		String u=UUID.randomUUID().toString();
		String uploadDir = uploadDirectory + "Vacancies/";
		
		Document document=new Document();
		document.setFileName(fileName);
		document.setFileType(multipartFile.getContentType());
		document.setSize(multipartFile.getSize());
		document.setNewFileName(u+fileName);
		document.setFileDownloadUri(uploadDir+u+fileName);
		
		vacancy.setDocument(document);
		
		vacancy.setGeneration_date(generationDate);
		vacancy.setLast_modified_date(generationDate);
		vacancy.setStatus("active");
		vacancy_dao.addVacancy(vacancy);
		
		System.out.println(uploadDir);
		File_Upload.saveFile(uploadDir, u+fileName, multipartFile);
	}

	@Override
	public void deleteVacancy(Vacancy vacancy) {
		vacancy_dao.deleteVacancy(vacancy);
	}

	@Override
	public List<Vacancy> getVacancyList() {
		return vacancy_dao.getVacancyList();
	}

	@Override
	public Document findByDocumentName(String fileName) {
		return vacancy_dao.findByDocumentName(fileName);
	}

	@Override
	public void updateVacancy(Vacancy vacancy) {
		Date date=Calendar.getInstance().getTime();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String CurrentDate=dateFormat.format(date);
		
		vacancy.setLast_modified_date(CurrentDate);
		vacancy_dao.updateVacancy(vacancy);
	}

	@Override
	public Vacancy findVacancyById(long job_id) {
		return vacancy_dao.findById(job_id);
	}

	@Override
	public List<Vacancy> getVacancyListByVisibility(String role) {
		return vacancy_dao.getVacancyListByVisibility(role);
	}

	@Override
	public List<Vacancy> getVacancyListByUser(long user_id) {
		return vacancy_dao.getVacancyListByUser(user_id);
	}

}
