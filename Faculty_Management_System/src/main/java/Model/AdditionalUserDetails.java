package Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name="user_details")
public class AdditionalUserDetails implements Serializable {
	
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long additionaluserdetails_id;
	   
	   @Column(name="Class")
	   @NotEmpty
	   private String Class;
	   
	   @Column(name="sem")
	   @NotEmpty
	   private String sem;
	   
	   @Column(name="blood_group")
	   @NotEmpty
	   private String blood_group;
	   
	   @Column(name="gender")
	   @NotEmpty
	   private String gender;
	   
	   @Column(name="marital_status")
	   @NotEmpty
	   private String marital_status;
	   
	   @Column(name="address")
	   @NotEmpty
	   private String address;
	   
	   @Column(name="city")
	   @NotEmpty
	   private String city;
	   
	   @Column(name="state")
	   @NotEmpty
	   private String state;
	   
	   @Column(name="pin_code")
	   @NotEmpty
	   private String pin_code;

	   @Column(name="father_name")
	   @NotEmpty
	   private String father_name;
	   
	   @Column(name="father_phno")
	   @NotEmpty
	   private String father_phno;
	   
	   @Column(name="father_occupation")
	   @NotEmpty
	   private String father_occupation;
	   
	   @Column(name="mother_name")
	   @NotEmpty
	   private String mother_name;
	   
	   @Column(name="mother_phno")
	   @NotEmpty
	   private String mother_phno;
	   
	   @Column(name="mother_occupation")
	   @NotEmpty
	   private String mother_occupation;
	   
	   @OneToOne(targetEntity = Document.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinColumn( name = "document_id")
	   private Document photo;
	   
	   @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinColumn( name = "user_id")
	    private User user;

	public AdditionalUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

/*
	public AdditionalUserDetails(long additionaluserdetails_id, String class1, String sem, String blood_group,
			String gender, String marital_status, String address, String city, String state, String pin_code,
			String father_name, String father_phno, String father_occupation, String mother_name, String mother_phno,
			String mother_occupation, Document photo) {
		super();
		this.additionaluserdetails_id = additionaluserdetails_id;
		Class = class1;
		this.sem = sem;
		this.blood_group = blood_group;
		this.gender = gender;
		this.marital_status = marital_status;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pin_code = pin_code;
		this.father_name = father_name;
		this.father_phno = father_phno;
		this.father_occupation = father_occupation;
		this.mother_name = mother_name;
		this.mother_phno = mother_phno;
		this.mother_occupation = mother_occupation;
		this.photo = photo;
	}*/
	

	public long getAdditionaluserdetails_id() {
		return additionaluserdetails_id;
	}

	public void setAdditionaluserdetails_id(long additionaluserdetails_id) {
		this.additionaluserdetails_id = additionaluserdetails_id;
	}

	public String getclass() {
		return Class;
	}

	public void setClass(String class1) {
		Class = class1;
	}

	public String getSem() {
		return sem;
	}

	public void setSem(String sem) {
		this.sem = sem;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin_code() {
		return pin_code;
	}

	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getFather_phno() {
		return father_phno;
	}

	public void setFather_phno(String father_phno) {
		this.father_phno = father_phno;
	}

	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	public String getMother_phno() {
		return mother_phno;
	}

	public void setMother_phno(String mother_phno) {
		this.mother_phno = mother_phno;
	}

	public Document getPhoto() {
		return photo;
	}

	public void setPhoto(Document photo) {
		this.photo = photo;
	}

	public String getFather_occupation() {
		return father_occupation;
	}


	public void setFather_occupation(String father_occupation) {
		this.father_occupation = father_occupation;
	}


	public String getMother_occupation() {
		return mother_occupation;
	}


	public void setMother_occupation(String mother_occupation) {
		this.mother_occupation = mother_occupation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	   
}
