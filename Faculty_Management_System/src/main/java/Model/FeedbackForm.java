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
@Table(name="feedback")
public class FeedbackForm {

	private static final long serialVersionUID = 1L;
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long form_id;
	   
	   @Column(name="form_title")
	   @NotNull
	   private String form_title;
	   
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
}
