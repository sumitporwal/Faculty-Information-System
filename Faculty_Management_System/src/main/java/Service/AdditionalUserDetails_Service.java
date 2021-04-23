package Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Model.AdditionalUserDetails;
import Model.Document;

public interface AdditionalUserDetails_Service {

	  public AdditionalUserDetails findByAdditionalUserDetails(String additional_user_details);
	  public void addAdditionalUserDetails(AdditionalUserDetails additional_user_details,MultipartFile multipartFile) throws IOException;
	  public void deleteAdditionalUserDetails(AdditionalUserDetails additional_user_details);
	  public List<AdditionalUserDetails> getAdditionalUserDetailsList();
	  public Document findByDocumentName(String fileName);
	  public void updateAdditionalUserDetails(AdditionalUserDetails additional_user_details,MultipartFile multipartFile) throws IOException;
	  public AdditionalUserDetails findAdditionalUserDetailsById(long additionaluserdetails_id);
}
