package DAO;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Model.AdditionalUserDetails;
import Model.Document;
import Model.Notification;
import Model.User;
import Service.User_Service;

@Repository
public class AdditionalUserDetails_DAO_Imp implements AdditionalUserDetails_DAO {

	@Autowired
	private SessionFactory sessionfactory;
	
	@Autowired
	private User_Service user_service;
	
	@Override
	public AdditionalUserDetails findByAdditionalUserDetails(String additional_user_details) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<AdditionalUserDetails> query = currentSession.createQuery("from AdditionalUserDetails where subject=:subject",AdditionalUserDetails.class);
		query.setParameter("subject", additional_user_details);
		List<AdditionalUserDetails> list = query.getResultList();
		AdditionalUserDetails additional_user_details1 = new AdditionalUserDetails();
		if (list.size() > 0) {
			additional_user_details1 = list.get(0);
		}
		return additional_user_details1;

	}

	@Override
	public void addAdditionalUserDetails(AdditionalUserDetails additional_user_details) {
		sessionfactory.getCurrentSession().save(additional_user_details);
	}

	@Override
	public void deleteAdditionalUserDetails(AdditionalUserDetails additional_user_details) {
		try {
			File file = new File(additional_user_details.getPhoto().getFileDownloadUri());
			file.delete();
			sessionfactory.getCurrentSession().delete(additional_user_details);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<AdditionalUserDetails> getAdditionalUserDetailsList() {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<AdditionalUserDetails> query = currentSession.createQuery("from AdditionalUserDetails", AdditionalUserDetails.class);
		List<AdditionalUserDetails> list = query.getResultList();
		return list;
	}

	@Override
	public void updateAdditionalUserDetails(AdditionalUserDetails additional_user_details) {
		try {
			Session currentSession = sessionfactory.getCurrentSession();
			AdditionalUserDetails ad = findById(additional_user_details.getAdditionaluserdetails_id());
		//	additional_user_details.getPhoto().setDocument_id(ad.getPhoto().getDocument_id());
			ad.setClass(additional_user_details.getclass());
		    ad.setSem(additional_user_details.getSem());
			ad.setBlood_group(additional_user_details.getBlood_group());
			ad.setGender(additional_user_details.getGender());
			ad.setMarital_status(additional_user_details.getMarital_status());
			ad.setAddress(additional_user_details.getAddress());
			ad.setCity(additional_user_details.getCity());
			ad.setState(additional_user_details.getState());
			ad.setPin_code(additional_user_details.getPin_code());
			ad.setFather_name(additional_user_details.getFather_name());
			ad.setFather_phno(additional_user_details.getFather_phno());
			ad.setFather_occupation(additional_user_details.getFather_occupation());
			ad.setMother_name(additional_user_details.getMother_name());
			ad.setMother_phno(additional_user_details.getMother_phno());
			ad.setMother_occupation(additional_user_details.getMother_occupation());
			ad.setPhoto(additional_user_details.getPhoto());
		//	ad.getUser().setUser_id(additional_user_details.getUser().getUser_id());
		//	ad.setUser(additional_user_details.getUser());
			user_service.updateUser(additional_user_details.getUser());
			currentSession.update(ad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public AdditionalUserDetails findById(long additionaluserdetails_id) {
		Session currentSession = sessionfactory.getCurrentSession();
		Query<AdditionalUserDetails> query = currentSession.createQuery("from AdditionalUserDetails where additionaluserdetails_id=:additionaluserdetails_id", AdditionalUserDetails.class);
		query.setParameter("additionaluserdetails_id", additionaluserdetails_id);
		List<AdditionalUserDetails> list = query.getResultList();
		System.out.println(list);
		AdditionalUserDetails additional_user_details1 = new AdditionalUserDetails();
		if (list.size() > 0) {
			additional_user_details1 = list.get(0);
		}
		return additional_user_details1;
	}

}
