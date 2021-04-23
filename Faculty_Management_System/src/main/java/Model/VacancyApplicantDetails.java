package Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="vacancy_applicant_details")
public class VacancyApplicantDetails {

	private static final long serialVersionUID = 1L;
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long applicantdetails_id;
	   
	   @Column(name="applicant_name")
	   @NotNull
	   private String applicant_name;
	   
	   @Column(name="applicant_email")
	   @NotNull
	   private String applicant_email;
	   
	   @Column(name="applicant_phno")
	   @NotNull
	   private String applicant_phno;
	   
	   @Column(name="applicant_dob")
	   @NotNull
	   private String applicant_dob;
	   
	   @Column(name="appliedOn")
	   @NotNull
	   private String appliedOn;
	   
	   @Column(name="linkedin_profile")
	   @NotNull
	   private String linkedin_profile;
	   
	   @OneToOne(targetEntity = Document.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinColumn( nullable = false,name = "document_id")
	   private Document document;
	   
	   @Column(name="status")
	   @NotNull
	   private String Status;
	   
	   @ManyToOne(targetEntity = Vacancy.class, fetch = FetchType.EAGER)
	    @JoinColumn( nullable = false,name = "job_id")
	    private Vacancy vacancy;
	   
	   @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	    @JoinColumn( nullable = false,name = "user_id")
	    private User user;

	public VacancyApplicantDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VacancyApplicantDetails(long applicantdetails_id, String applicant_name, String applicant_email,
			String applicant_phno, String applicant_dob, String appliedOn, String linkedin_profile, Document document,
			String status, Vacancy vacancy, User user) {
		super();
		this.applicantdetails_id = applicantdetails_id;
		this.applicant_name = applicant_name;
		this.applicant_email = applicant_email;
		this.applicant_phno = applicant_phno;
		this.applicant_dob = applicant_dob;
		this.appliedOn = appliedOn;
		this.linkedin_profile = linkedin_profile;
		this.document = document;
		Status = status;
		this.vacancy = vacancy;
		this.user = user;
	}



	public long getApplicantdetails_id() {
		return applicantdetails_id;
	}

	public void setApplicantdetails_id(long applicantdetails_id) {
		this.applicantdetails_id = applicantdetails_id;
	}

	public String getApplicant_name() {
		return applicant_name;
	}

	public void setApplicant_name(String applicant_name) {
		this.applicant_name = applicant_name;
	}

	public String getApplicant_email() {
		return applicant_email;
	}

	public void setApplicant_email(String applicant_email) {
		this.applicant_email = applicant_email;
	}

	public String getApplicant_phno() {
		return applicant_phno;
	}
	

	public void setApplicant_phno(String applicant_phno) {
		this.applicant_phno = applicant_phno;
	}
	

	public String getApplicant_dob() {
		return applicant_dob;
	}

	public void setApplicant_dob(String applicant_dob) {
		this.applicant_dob = applicant_dob;
	}

	public String getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(String appliedOn) {
		this.appliedOn = appliedOn;
	}

	public String getLinkedin_profile() {
		return linkedin_profile;
	}

	public void setLinkedin_profile(String linkedin_profile) {
		this.linkedin_profile = linkedin_profile;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Vacancy getVacancy() {
		return vacancy;
	}

	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
