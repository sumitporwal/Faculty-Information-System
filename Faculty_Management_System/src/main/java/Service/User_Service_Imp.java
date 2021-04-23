package Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import DAO.Permission_DAO;
import DAO.ResetPasswordToken_DAO;
import DAO.ResetPasswordToken_DAO_Imp;
import DAO.Role_DAO;
import DAO.User_DAO;
import Model.AdditionalUserDetails;
import Model.Document;
import Model.Permission;
import Model.ResetPasswordToken;
import Model.Role;
import Model.User;
import Upload.File_Upload;

@Service
@Transactional
public class User_Service_Imp implements User_Service{
	
	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	private User_DAO user_dao;  
	
	@Autowired
	 private Role_DAO role_dao;
	
	@Autowired
	 private Permission_DAO permission_dao;
	
	@Autowired
	 private Role_Service role_service;
	
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	 @Autowired
	 private ResetPasswordToken_DAO resetpasswordtoken_dao;
	 
	@Override
	public void saveUser(User user,String role) {
		  User userExists =  user_dao.findByEmail(user.getEmail());
		  if(userExists.getEmail() == null) {
		  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		  System.out.println(user.getPassword());
		  user.setEnabled(true);
		  Role userRole = role_dao.findByRole(role);
		  if(userRole.getRole_name()==null) {
			  userRole.setRole_name(role);
			  role_service.addRole(userRole);
		  }
		  user.setRoles((userRole));
		  Permission permission = permission_dao.findPermissionByRole(role);
		  user.setPermissions(permission);
		  user_dao.saveUser(user);
		  }
	}
	
	@Override
	public List<User> getUsersByRole(String role) {
		return user_dao.getUsersByRole(role);
	}

	@Override
	public User findUserByEmail(String email) {
		return user_dao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 User user = user_dao.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("Invalid username or password.");
	        }
	        else if(user.isEnabled()) {
	          return new org.springframework.security.core.userdetails.User(user.getEmail(),
	            user.getPassword(),
	            mapRolesToAuthorities(user.getRoles()));
	        }
	        return null;
	    }

	    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Role roles) {
	         GrantedAuthority authority = new GrantedAuthority() {
                private static final long serialVersionUID = 4866292482579095227L;

                public String getAuthority() {
                    return roles.getRole_name();
                }
            };
            Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            list.add(authority);
            return list;

	    }
	    
	    @Override
		public void changeUserPassword(User user, String password,String token) {
	        String encodedPassword = bCryptPasswordEncoder.encode(password);
	        System.out.println(user.getPassword());
	        user.setPassword(encodedPassword);
	        System.out.println(user.getPassword());
	        System.out.println(user.getUser_id());
	        ResetPasswordToken resetpasswordtoken=resetpasswordtoken_dao.findByToken(token);
	        resetpasswordtoken.setToken(null);
	        user_dao.updateUser(user);
		}

		@Override
		public User findUserById(long user_id) {
			return user_dao.findById(user_id);
		}

		@Override
		public void updateUser(User user) {
		/*	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			String u=UUID.randomUUID().toString();
			String uploadDir = uploadDirectory + "Images/";
			if(fileName==null) {
				Document document=new Document();
				document.setFileName(fileName);
				document.setFileType(multipartFile.getContentType());
				document.setSize(multipartFile.getSize());
				document.setNewFileName(u+fileName);
				document.setFileDownloadUri(uploadDir+u+fileName);
				user.getAdditional_user_details().setPhoto(document);
			}*/
			
			user_dao.updateUser(user);
		//	File_Upload.saveFile(uploadDir, u+fileName, multipartFile);
		}
		
		@Override
		public void deleteUser(User user) {
			user_dao.deleteUser(user);
		}
}

