package Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Model.Document;
import Model.Vacancy;

public interface Vacancy_Service {

	  public Vacancy findByVacancy(String vacancy);
	  public void addVacancy(Vacancy vacancy,MultipartFile multipartFile) throws IOException;
	  public void deleteVacancy(Vacancy vacancy);
	  public List<Vacancy> getVacancyList();
	  public Document findByDocumentName(String fileName);
	  public void updateVacancy(Vacancy vacancy);
	  public Vacancy findVacancyById(long job_id);
	  public List<Vacancy> getVacancyListByVisibility(String role);
	  public List<Vacancy> getVacancyListByUser(long user_id);
}
