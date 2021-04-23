package DAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Model.Document;
import Model.Vacancy;
import Model.VacancyApplicantDetails;

@Repository
public class Vacancy_Applicants_Details_DAO_Imp implements Vacancy_Applicants_Details_DAO {

	@Autowired
	private SessionFactory sessionfactory;
	
	@Override
	public VacancyApplicantDetails findByVacancyApplicantDetails(String vacancy_applicant_details) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<VacancyApplicantDetails> query = currentSession.createQuery("from vacancy_applicant_details where subject=:subject",VacancyApplicantDetails.class);
		query.setParameter("subject", vacancy_applicant_details);
		List<VacancyApplicantDetails> list = query.getResultList();
		VacancyApplicantDetails vacancy_applicant_details1 = new VacancyApplicantDetails();
		if (list.size() > 0) {
			vacancy_applicant_details1 = list.get(0);
		}
		return vacancy_applicant_details1;
	}

	@Override
	public void addVacancyApplicantDetails(VacancyApplicantDetails vacancy_applicant_details) {
		sessionfactory.getCurrentSession().save(vacancy_applicant_details);
	}

	@Override
	public void deleteVacancyApplicantDetails(VacancyApplicantDetails vacancy_applicant_details) {
		try {
			File file = new File(vacancy_applicant_details.getDocument().getFileDownloadUri());
			file.delete();
			sessionfactory.getCurrentSession().delete(vacancy_applicant_details);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<VacancyApplicantDetails> getVacancyApplicantDetailsList() {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<VacancyApplicantDetails> query = currentSession.createQuery("from VacancyApplicantDetails", VacancyApplicantDetails.class);
		List<VacancyApplicantDetails> list = query.getResultList();
		return list;
	}

	@Override
	public void updateVacancyApplicantDetails(VacancyApplicantDetails vacancy_applicant_details) {
		try {
			Session currentSession = sessionfactory.getCurrentSession();
			VacancyApplicantDetails v = findVacancyApplicantDetailsByApplicantId(vacancy_applicant_details.getApplicantdetails_id());
			v.setApplicant_name(vacancy_applicant_details.getApplicant_name());
			v.setApplicant_email(vacancy_applicant_details.getApplicant_email());
			v.setApplicant_phno(vacancy_applicant_details.getApplicant_phno());
			v.setApplicant_dob(vacancy_applicant_details.getApplicant_dob());
			v.setAppliedOn(v.getAppliedOn());
			v.setLinkedin_profile(vacancy_applicant_details.getLinkedin_profile());
			v.setStatus(vacancy_applicant_details.getStatus());
			v.setDocument(v.getDocument());
			v.setUser(v.getUser());
			currentSession.update(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public VacancyApplicantDetails findById(long job_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<VacancyApplicantDetails> query = currentSession.createQuery("from VacancyApplicantDetails where job_id=:job_id", VacancyApplicantDetails.class);
		query.setParameter("job_id", job_id);
		List<VacancyApplicantDetails> list = query.getResultList();
		System.out.println(list);
		VacancyApplicantDetails vacancy_applicant_details1 = new VacancyApplicantDetails();
		if (list.size() > 0) {
			vacancy_applicant_details1 = list.get(0);
		}
		return vacancy_applicant_details1;
	}

	@Override
	public VacancyApplicantDetails findVacancyApplicantDetailsByApplicantId(long applicantdetails_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<VacancyApplicantDetails> query = currentSession.createQuery("from VacancyApplicantDetails where applicantdetails_id=:applicantdetails_id and status='Pending'", VacancyApplicantDetails.class);
		query.setParameter("applicantdetails_id", applicantdetails_id);
		List<VacancyApplicantDetails> list = query.getResultList();
		System.out.println(list);
		VacancyApplicantDetails vacancy_applicant_details1 = new VacancyApplicantDetails();
		if (list.size() > 0) {
			vacancy_applicant_details1 = list.get(0);
		}
		return vacancy_applicant_details1;
	}

	@Override
	public List<Vacancy> findVacancyApplicantDetailsByReferrenceId(long user_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<VacancyApplicantDetails> query = currentSession.createQuery("from VacancyApplicantDetails where user_id=:user_id", VacancyApplicantDetails.class);
		query.setParameter("user_id", user_id);
		List<VacancyApplicantDetails> list = query.getResultList();
		System.out.println(list);
		List<Vacancy> list1 = new ArrayList<Vacancy>();
		for(int i=0;i<list.size();i++) {
			Session currentSession1 = sessionfactory.getCurrentSession();
			Query<Vacancy> query1 = currentSession.createQuery("from Vacancy where job_id=:job_id", Vacancy.class);
			query1.setParameter("job_id", list.get(i).getVacancy().getJob_id());
			list1.addAll(query1.getResultList());
		}
		return list1;
	}

}
