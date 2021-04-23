package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permission")
public class Permission {
	private static final long serialVersionUID = 1L;
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long permission_id;
	   private String permission_name;
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Permission(long permission_id, String permission_name) {
		super();
		this.permission_id = permission_id;
		this.permission_name = permission_name;
	}
	public long getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(long permission_id) {
		this.permission_id = permission_id;
	}
	public String getPermission_name() {
		return permission_name;
	}
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
