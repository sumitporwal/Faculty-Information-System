package Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import Model.Role;
import Model.User;

public class AuthenticateUserService_Impl {
	
	@Autowired
	User user;

	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        Set<Role> roles = (Set<Role>) user.getRoles();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	         
	        for (Role role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
	        }
	         
	        return authorities;
	    }
	 

	    public String getPassword() {
	        return user.getPassword();
	    }
	 

	    public String getUsername() {
	        return user.getUsername();
	    }
	 

	    public boolean isAccountNonExpired() {
	        return true;
	    }
	 

	    public boolean isAccountNonLocked() {
	        return true;
	    }
	 

	    public boolean isCredentialsNonExpired() {
	        return true;
	    }
	 

	   /* public boolean isEnabled() {
	        return user.isEnabled();
	    }*/
	 
	}


