package DAO;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Model.AdditionalUserDetails;
import Model.Document;

public interface AdditionalUserDetails_DAO {

	  public AdditionalUserDetails findByAdditionalUserDetails(String additional_user_details);
	  public void addAdditionalUserDetails(AdditionalUserDetails additional_user_details);
	  public void deleteAdditionalUserDetails(AdditionalUserDetails additional_user_details);
	  public List<AdditionalUserDetails> getAdditionalUserDetailsList();
	  public void updateAdditionalUserDetails(AdditionalUserDetails additional_user_details);
	  public AdditionalUserDetails findById(long additionaluserdetails_id);
}
