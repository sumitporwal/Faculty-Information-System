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

import DAO.Vacancy_Applicants_Details_DAO;
import Model.Document;
import Model.Vacancy;
import Model.VacancyApplicantDetails;
import Upload.File_Upload;

@Service
@Transactional
public class Vacancy_Applicant_Details_Service_Imp implements Vacancy_Applicant_Details_Service {

	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	Vacancy_Applicants_Details_DAO vacancy_applicant_details_dao;
	
	@Override
	public VacancyApplicantDetails findByVacancyApplicantDetails(String vacancy_applicant_details) {
		return vacancy_applicant_details_dao.findByVacancyApplicantDetails(vacancy_applicant_details);
	}

	@Override
	public void addVacancyApplicantDetails(VacancyApplicantDetails vacancy_applicant_details, MultipartFile multipartFile) throws IOException {
		 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			Date date=Calendar.getInstance().getTime();
			DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String generationDate=dateFormat.format(date);
			System.out.println(date);
			System.out.println(generationDate);
			
			String u=UUID.randomUUID().toString();
			String uploadDir = uploadDirectory + "VacancyApplicantDetails/";
			
			Document document=new Document();
			document.setFileName(fileName);
			document.setFileType(multipartFile.getContentType());
			document.setSize(multipartFile.getSize());
			document.setNewFileName(u+fileName);
			document.setFileDownloadUri(uploadDir+u+fileName);
			
			vacancy_applicant_details.setDocument(document);
			
			vacancy_applicant_details.setAppliedOn(generationDate);
			vacancy_applicant_details.setStatus("Pending");
			vacancy_applicant_details_dao.addVacancyApplicantDetails(vacancy_applicant_details);
			
			System.out.println(uploadDir);
			File_Upload.saveFile(uploadDir, u+fileName, multipartFile);
	}

	@Override
	public void deleteVacancyApplicantDetails(VacancyApplicantDetails vacancy_applicant_details) {
		vacancy_applicant_details_dao.deleteVacancyApplicantDetails(vacancy_applicant_details);
	}

	@Override
	public List<VacancyApplicantDetails> getVacancyApplicantDetailsList() {
		return vacancy_applicant_details_dao.getVacancyApplicantDetailsList();
	}

	@Override
	public void updateVacancyApplicantDetails(VacancyApplicantDetails vacancy_applicant_details) {
		vacancy_applicant_details_dao.updateVacancyApplicantDetails(vacancy_applicant_details);
	}

	@Override
	public VacancyApplicantDetails findVacancyApplicantDetailsById(long job_id) {
		return vacancy_applicant_details_dao.findById(job_id);
	}

	@Override
	public VacancyApplicantDetails findVacancyApplicantDetailsByApplicantId(long applicantdetails_id) {
		return vacancy_applicant_details_dao.findVacancyApplicantDetailsByApplicantId(applicantdetails_id);
	}

	@Override
	public List<Vacancy> findVacancyApplicantDetailsByReferrenceId(long user_id) {
		return vacancy_applicant_details_dao.findVacancyApplicantDetailsByReferrenceId(user_id);
	}
}
