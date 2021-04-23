package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import javax.persistence.JoinColumn;


import com.sun.istack.NotNull;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id", unique = true, nullable = false)
   private long user_id;
   
   @Column(name="name")
   @NotEmpty
   private String name;
   
   @Column(name="email")
   @NotEmpty(message="email field is empty") 
   @Email(message="Not a valid Email")
   private String email;
   
   @Column(name="phno")
   @NotEmpty
   private String phno;
   
   @Column(name="dob")
   @NotEmpty
   private String dob;
   
   @Column(name="stream")
   @NotEmpty
   private String stream;
   
   @Column(name="department")
   @NotEmpty
   private String department;
   
   @Column(name="password")
   @NotEmpty
   private String password;
   
   @Column(name="username")
   @NotEmpty
   private String username;
   
   @Column(name = "enabled")
   @NotEmpty
   private boolean enabled;
   
   @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
   @JoinTable(
           name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
           )
   @ManyToOne
   private Role roles;
   
   @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
   @JoinTable(
           name = "users_permissions",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "permission_id")
           )
   @ManyToOne
   private Permission permissions;
   
 /*  @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "user")
   private AdditionalUserDetails additional_user_details;*/

public User() {
	super();
	// TODO Auto-generated constructor stub
}

/*
public User(long user_id, String name,
		@NotEmpty(message = "email field is empty") @Email(message = "Not a valid Email") String email,
		String phno, String dob, String stream, String department, String password, String username, boolean enabled,
		Role roles, Permission permissions, AdditionalUserDetails additional_user_details) {
	super();
	this.user_id = user_id;
	this.name = name;
	this.email = email;
	this.phno = phno;
	this.dob = dob;
	this.stream = stream;
	this.department = department;
	this.password = password;
	this.username = username;
	this.enabled = enabled;
	this.roles = roles;
	this.permissions = permissions;
	this.additional_user_details = additional_user_details;
}*/


public long getUser_id() {
	return user_id;
}

public void setUser_id(long user_id) {
	this.user_id = user_id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhno() {
	return phno;
}

public void setPhno(String phno) {
	this.phno = phno;
}

public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}

public String getStream() {
	return stream;
}

public void setStream(String stream) {
	this.stream = stream;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public Role getRoles() {
	return roles;
}

public void setRoles(Role roles) {
	this.roles = roles;
}

public Permission getPermissions() {
	return permissions;
}

public void setPermissions(Permission permissions) {
	this.permissions = permissions;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}
/*
public AdditionalUserDetails getAdditional_user_details() {
	return additional_user_details;
}

public void setAdditional_user_details(AdditionalUserDetails additional_user_details) {
	this.additional_user_details = additional_user_details;
}*/
   
}
