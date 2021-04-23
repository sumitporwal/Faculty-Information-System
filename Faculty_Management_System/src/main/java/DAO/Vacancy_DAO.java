package DAO;

import java.util.List;

import Model.Document;
import Model.Vacancy;

public interface Vacancy_DAO {

	public Vacancy findByVacancy(String vacancy);
	public void addVacancy(Vacancy vacancy);
	public void deleteVacancy(Vacancy vacancy);
	public List<Vacancy> getVacancyList();
	public Document findByDocumentName(String fileName);
	public void updateVacancy(Vacancy vacancy);
	public Vacancy findById(long job_id);
	public List<Vacancy> getVacancyListByVisibility(String role);
	public List<Vacancy> getVacancyListByUser(long user_id);
}
