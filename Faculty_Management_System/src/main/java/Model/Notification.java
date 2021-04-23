package Model;

import java.util.UUID;

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

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

@Entity
@Table(name="notification")
public class Notification {
	
	private static final long serialVersionUID = 1L;
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long notification_id;
	   
	   @Column(name="subject")
	   @NotNull
	   private String subject;
	   
	   @Column(name="content")
	   @NotNull
	   private String content;
	   
	   @Column(name="generation_date")
	   @NotNull
	   private String generation_date;
	   
	   @Column(name="due_date")
	   @NotNull
	   private String due_date;

	   @Column(name="last_modified_date")
	   @NotNull
	   private String last_modified_date;
	   
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

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification(long notification_id, String subject, String content, String generation_date, String due_date,
			String last_modified_date, String visibility, Document document, String status, User user) {
		super();
		this.notification_id = notification_id;
		this.subject = subject;
		this.content = content;
		this.generation_date = generation_date;
		this.due_date = due_date;
		this.last_modified_date = last_modified_date;
		this.visibility = visibility;
		this.document = document;
		Status = status;
		this.user = user;
	}



	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public long getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(long notification_id) {
		this.notification_id = notification_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGeneration_date() {
		return generation_date;
	}

	public void setGeneration_date(String generation_date) {
		this.generation_date = generation_date;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	
	public String getLast_modified_date() {
		return last_modified_date;
	}

	public void setLast_modified_date(String last_modified_date) {
		this.last_modified_date = last_modified_date;
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
