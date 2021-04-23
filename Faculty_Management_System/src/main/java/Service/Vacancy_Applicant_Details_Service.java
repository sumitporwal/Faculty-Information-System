package Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Model.Vacancy;
import Model.VacancyApplicantDetails;


public interface Vacancy_Applicant_Details_Service {

	public VacancyApplicantDetails findByVacancyApplicantDetails(String vacancy_applicant_details);
	public void addVacancyApplicantDetails(VacancyApplicantDetails vacancy_applicant_details, MultipartFile multipartFile) throws IOException;
	public void deleteVacancyApplicantDetails(VacancyApplicantDetails vacancy_applicant_details);
	public List<VacancyApplicantDetails> getVacancyApplicantDetailsList();
	public void updateVacancyApplicantDetails(VacancyApplicantDetails vacancy_applicant_details);
	public VacancyApplicantDetails findVacancyApplicantDetailsById(long job_id);
	public VacancyApplicantDetails findVacancyApplicantDetailsByApplicantId(long applicantdetails_id);
	public List<Vacancy> findVacancyApplicantDetailsByReferrenceId(long user_id);
}
