package Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="vacancy")
public class Vacancy {

	private static final long serialVersionUID = 1L;
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long job_id;
	   
	   @Column(name="job_title")
	   @NotNull
	   private String job_title;
	   
	   @Column(name="job_description")
	   @NotNull
	   private String job_description;
	   
	   @Column(name="generation_date")
	   @NotNull
	   private String generation_date;
	   
	   @Column(name="application_due_date")
	   @NotNull
	   private String application_due_date;

	   @Column(name="last_modified_date")
	   @NotNull
	   private String last_modified_date;
	   
	   @Column(name="location")
	   @NotNull
	   private String location;
	   
	   @Column(name="employment_type")
	   @NotNull
	   private String employment_type;
	   
	   @Column(name="visibility")
	   @NotNull
	   private String visibility;
	   
	   @OneToOne(targetEntity = Document.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinColumn( nullable = false,name = "document_id")
	   private Document document;
	   
	   @Column(name="status")
	   @NotNull
	   private String Status;
	   
	   @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	    @JoinColumn( nullable = false,name = "user_id")
	    private User user;

	public Vacancy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vacancy(long job_id, String job_title, String job_description, String generation_date,
			String application_due_date, String last_modified_date, String location, String employment_type,
			String visibility, Document document, String status, User user) {
		super();
		this.job_id = job_id;
		this.job_title = job_title;
		this.job_description = job_description;
		this.generation_date = generation_date;
		this.application_due_date = application_due_date;
		this.last_modified_date = last_modified_date;
		this.location = location;
		this.employment_type = employment_type;
		this.visibility = visibility;
		this.document = document;
		Status = status;
		this.user = user;
	}

	public long getJob_id() {
		return job_id;
	}

	public void setJob_id(long job_id) {
		this.job_id = job_id;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getJob_description() {
		return job_description;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}

	public String getGeneration_date() {
		return generation_date;
	}

	public void setGeneration_date(String generation_date) {
		this.generation_date = generation_date;
	}

	public String getApplication_due_date() {
		return application_due_date;
	}

	public void setApplication_due_date(String application_due_date) {
		this.application_due_date = application_due_date;
	}

	public String getLast_modified_date() {
		return last_modified_date;
	}

	public void setLast_modified_date(String last_modified_date) {
		this.last_modified_date = last_modified_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmployment_type() {
		return employment_type;
	}

	public void setEmployment_type(String employment_type) {
		this.employment_type = employment_type;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
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
