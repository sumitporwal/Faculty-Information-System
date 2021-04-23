package Model;




import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ResetPasswordToken {
	private static final int EXPIRATION = 5;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String token;
 
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
 
    private LocalDateTime creationDate;

	public ResetPasswordToken(Long id, String token, User user, LocalDateTime creationDate) {
		super();
		this.id = id;
		this.token = token;
		this.user = user;
		this.creationDate = creationDate;
	}
	
	public ResetPasswordToken(String token, User user, LocalDateTime creationDate) {
		super();
		this.token = token;
		this.user = user;
		this.creationDate = creationDate;
	}
	

	public ResetPasswordToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}



	public ResetPasswordToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public int getExpiration() {
		return EXPIRATION;
	}
    
    

}
