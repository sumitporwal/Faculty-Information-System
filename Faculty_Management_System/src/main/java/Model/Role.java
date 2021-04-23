package Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long role_id;
   private String role_name;
   
   @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
   @JoinTable(
           name = "roles_permissions",
           joinColumns = @JoinColumn(name = "role_id"),
           inverseJoinColumns = @JoinColumn(name = "permission_id")
           )
   @ManyToOne
   private Permission permissions;

public Role() {
	super();
	// TODO Auto-generated constructor stub
}

public Role(long role_id, String role_name, Permission permissions) {
	super();
	this.role_id = role_id;
	this.role_name = role_name;
	this.permissions = permissions;
}

public long getRole_id() {
	return role_id;
}

public void setRole_id(long role_id) {
	this.role_id = role_id;
}

public String getRole_name() {
	return role_name;
}

public void setRole_name(String role_name) {
	this.role_name = role_name;
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
   
      
}
