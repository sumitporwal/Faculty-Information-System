package Controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Model.AdditionalUserDetails;
import Model.Document;
import Model.Notification;
import Model.User;
import Service.Notification_Service;
import Service.User_Service;
import Upload.File_Upload;


@RestController
@CrossOrigin
public class User_Controller {
	
	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	 private User_Service user_service;
	
	@RequestMapping(value= {"/"}, method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
	
	 @RequestMapping(value= {"/signupAdmin"}, method=RequestMethod.GET)
	 public ModelAndView signupAdmin(Model model) {
	  ModelAndView mv = new ModelAndView();
	  User user = new User();
	  model.addAttribute("user", user);
	  mv.setViewName("Admin/registrationadmin");
	  
	  return mv;
	 }
	 
	 @RequestMapping(value= {"/signupTeacher"}, method=RequestMethod.GET)
	 public ModelAndView signupTeacher(Model model) {
	  ModelAndView mv = new ModelAndView();
	  User user = new User();
	  model.addAttribute("user", user);
	  mv.setViewName("Admin/registrationadmin");
	  
	  return mv;
	 }
	 
	 @RequestMapping(value= {"/signupStudent"}, method=RequestMethod.GET)
	 public ModelAndView signupStudent(Model model) {
	  ModelAndView mv = new ModelAndView();
	  User user = new User();
	  model.addAttribute("user", user);
	  mv.setViewName("Admin/registrationadmin");
	  
	  return mv;
	 }
	 
	 @RequestMapping(value= {"/signupAdmin"}, method=RequestMethod.POST)
	 public ModelAndView createAdminUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,Model model,HttpServletRequest request) {
	  String referer=request.getHeader("Referer");	 
	  ModelAndView mv = new ModelAndView();
	  User userExists =  user_service.findUserByEmail(user.getEmail());
	  System.out.println(userExists.getEmail());
	  if(userExists.getEmail() != null) {
	   bindingResult.rejectValue("email", "error.user", "This email already exists!");
	  }
	  if(bindingResult.hasErrors()) {
	   System.out.println(bindingResult.getAllErrors());
	   System.out.println(new FieldError("user","email","error").getField());
	   mv.setViewName("500");
	  } else {
	   AdditionalUserDetails additional_user_details=new AdditionalUserDetails();
	//   user.setAdditional_user_details(additional_user_details);
	   user_service.saveUser(user,"ADMIN");
	   model.addAttribute("msg", "User has been registered successfully!");
	   model.addAttribute("user", new User());
	   mv.setViewName("redirect:"+referer);
	  }
	  
	 return mv;
	 }
	 
	 @RequestMapping(value= {"/signupTeacher"}, method=RequestMethod.POST)
	 public ModelAndView createUser(@Valid  @ModelAttribute("user") User user, BindingResult bindingResult,Model model,HttpServletRequest request) {
		 String referer=request.getHeader("Referer");
		 ModelAndView mv = new ModelAndView();
		  User userExists =  user_service.findUserByEmail(user.getEmail());
		  System.out.println(userExists.getEmail());
		  if(userExists.getEmail() != null) {
		   bindingResult.rejectValue("email", "error.user", "This email already exists!");
		  }
		  if(bindingResult.hasErrors()) {
		   System.out.println(bindingResult.getAllErrors());
		   System.out.println(new FieldError("user","email","error").getField());
		   mv.setViewName("500");
		  } else {
		   AdditionalUserDetails additional_user_details=new AdditionalUserDetails();
	//	   user.setAdditional_user_details(additional_user_details);
		   user_service.saveUser(user,"FACULTY");
		   model.addAttribute("msg", "User has been registered successfully!");
		   model.addAttribute("user", new User());
		   mv.setViewName("redirect:"+referer);
		  }
		  
		  return mv;
	 }
	 
	 @RequestMapping(value= {"/signupStudent"}, method=RequestMethod.POST)
	 public ModelAndView createSudentUser(@Valid  @ModelAttribute("user") User user, BindingResult bindingResult,Model model,HttpServletRequest request) {
		 String referer=request.getHeader("Referer");
		 ModelAndView mv = new ModelAndView();
		  User userExists =  user_service.findUserByEmail(user.getEmail());
		  System.out.println(userExists.getEmail());
		  if(userExists.getEmail() != null) {
		   bindingResult.rejectValue("email", "error.user", "This email already exists!");
		  }
		  if(bindingResult.hasErrors()) {
		   System.out.println(bindingResult.getAllErrors());
		   System.out.println(new FieldError("user","email","error").getField());
		   mv.setViewName("500");
		  } else {
		   AdditionalUserDetails additional_user_details=new AdditionalUserDetails();
		//   user.setAdditional_user_details(additional_user_details);
		   user_service.saveUser(user,"STUDENT");
		   model.addAttribute("msg", "User has been registered successfully!");
		   model.addAttribute("user", new User());
		   mv.setViewName("redirect:"+referer);
		  }
		  
		  return mv;	 
	 }
	 
	 @RequestMapping(path = "/updateuser/{id}", method = RequestMethod.POST)
		public ModelAndView updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,@PathVariable("id") long user_id,HttpServletRequest request){
		 String referer=request.getHeader("Referer");
		 ModelAndView mv = new ModelAndView();
			User user1=user_service.findUserById(user_id);
			System.out.println(user.isEnabled());
			System.out.println(user1.getUser_id());
			if(user1.getUser_id() <= 0) {
				   bindingResult.rejectValue("email", "error.user", "User does not Exists!");
				  }
				  if(bindingResult.hasErrors()) {
				   System.out.println(bindingResult.getAllErrors());
				   System.out.println(new FieldError("user","email","error").getField());
				   bindingResult.rejectValue("email", "error.user", "User does not Exists!");
				  } else if(user1.getUser_id() > 0) {
					   user.setUser_id(user_id);
			           user_service.updateUser(user);
			           mv.setViewName("redirect:"+ referer);
				  }
				 return mv;
	 }

	 @RequestMapping(path = "/findUser/{id}", method = RequestMethod.GET)
	    public User findTask(@PathVariable("id") long user_id){
	        return user_service.findUserById(user_id);
	  }
		
		
		@RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
		public ModelAndView deleteUser(@PathVariable("id") long user_id,HttpServletRequest request) {
			User user=user_service.findUserById(user_id);
			String referer=request.getHeader("Referer");
			ModelAndView mv=new ModelAndView();
			if(user!=null) {
			user_service.deleteUser(user);
			mv.setViewName("redirect:"+referer);
			}
			return mv;
		}
					
	 
	 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
	 public ModelAndView accessDenied() {
	  ModelAndView model = new ModelAndView();
	  model.setViewName("403");
	  return model;
	 }
}
