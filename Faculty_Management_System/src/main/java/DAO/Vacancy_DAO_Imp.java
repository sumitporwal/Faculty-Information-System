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
import Model.Vacancy;

@Repository
public class Vacancy_DAO_Imp implements Vacancy_DAO {
	
	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public Vacancy findByVacancy(String vacancy) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Vacancy> query = currentSession.createQuery("from vacancy where subject=:subject",
				Vacancy.class);
		query.setParameter("subject", vacancy);
		List<Vacancy> list = query.getResultList();
		Vacancy vacancy1 = new Vacancy();
		if (list.size() > 0) {
			vacancy1 = list.get(0);
		}
		return vacancy1;
	}

	@Override
	public void addVacancy(Vacancy vacancy) {
		sessionfactory.getCurrentSession().save(vacancy);
	}

	@Override
	public void deleteVacancy(Vacancy vacancy) {
		try {
			File file = new File(vacancy.getDocument().getFileDownloadUri());
			file.delete();
			sessionfactory.getCurrentSession().delete(vacancy);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Vacancy> getVacancyList() {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Vacancy> query = currentSession.createQuery("from Vacancy", Vacancy.class);
		List<Vacancy> list = query.getResultList();
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
	public void updateVacancy(Vacancy vacancy) {
		try {
			Session currentSession = sessionfactory.getCurrentSession();
			Vacancy v = findById(vacancy.getJob_id());
			v.setJob_title(vacancy.getJob_title());
			v.setJob_description(vacancy.getJob_description());
			v.setLocation(vacancy.getLocation());
			v.setEmployment_type(vacancy.getEmployment_type());
			v.setGeneration_date(v.getGeneration_date());
			v.setApplication_due_date(vacancy.getApplication_due_date());
			v.setLast_modified_date(vacancy.getLast_modified_date());
			v.setStatus(vacancy.getStatus());
			v.setVisibility(vacancy.getVisibility());
			v.setDocument(v.getDocument());
			v.setUser(v.getUser());
			currentSession.update(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Vacancy findById(long job_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Vacancy> query = currentSession.createQuery("from Vacancy where job_id=:job_id", Vacancy.class);
		query.setParameter("job_id", job_id);
		List<Vacancy> list = query.getResultList();
		System.out.println(list);
		Vacancy vacancy1 = new Vacancy();
		if (list.size() > 0) {
			vacancy1 = list.get(0);
		}
		return vacancy1;
	}

	@Override
	public List<Vacancy> getVacancyListByVisibility(String role) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Vacancy> query = currentSession.createQuery("from Vacancy", Vacancy.class);
		List<Vacancy> list = query.getResultList();
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

	@Override
	public List<Vacancy> getVacancyListByUser(long user_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<Vacancy> query = currentSession.createQuery("from Vacancy where user_id=:user_id", Vacancy.class);
		query.setParameter("user_id", user_id);
		List<Vacancy> list = query.getResultList();
		return list;
	}

}
