package Controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Model.AdditionalUserDetails;
import Model.Document;
import Model.Notification;
import Model.User;
import Service.AdditionalUserDetails_Service;
import Service.Notification_Service;
import Service.User_Service;

@RestController
@CrossOrigin
public class AdditionalUserDetailsController {

	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	 private User_Service user_service;
	
	@Autowired
	  private AdditionalUserDetails_Service additionaluserdetails_service;

	@RequestMapping(value= {"/addAdditionalUserDetails/{id}"}, method=RequestMethod.POST)
	 public ModelAndView addAdditionalUSerDetails(@Valid @ModelAttribute("additional_user_details") AdditionalUserDetails additional_user_details, BindingResult bindingResult,
			 @RequestParam("file") MultipartFile multipartFile,Model model,HttpServletRequest request,HttpSession session,Principal principal,@PathVariable("id") long user_id) throws IOException{
	  String referer=request.getHeader("Referer");	 
	  ModelAndView mv = new ModelAndView();
	  if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
	  User user1 =  user_service.findUserByEmail(userDetails.getUsername());
	  User user= user_service.findUserById(user_id);
	  if(bindingResult.hasErrors()) {
		  System.out.println(bindingResult.getAllErrors());
	   mv.setViewName("500");
	  } else {
	   additional_user_details.getUser().setUser_id(user_id);
	   additional_user_details.getUser().setRoles(user.getRoles());
	   additional_user_details.getUser().setPermissions(user.getPermissions());
	   additional_user_details.getUser().setPassword(user.getPassword());
	   additionaluserdetails_service.addAdditionalUserDetails(additional_user_details,multipartFile);
	   model.addAttribute("msg", "Details added successfully!");
	   model.addAttribute("user", new User());
	   mv.setViewName("redirect:"+referer);
	  }
	  
	 return mv;
	  }
     else {
	    mv.setViewName("signin");
       return mv;
     }
	}
	/*
	 @RequestMapping(path = "/media/download/{fileName:.+}", method = RequestMethod.GET)
	 public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
				@PathVariable("fileName") String fileName) throws IOException {
		    
		    Document document=additionaluserdetails_service.findByDocumentName(fileName);
        
			File file = new File(document.getFileDownloadUri());
			System.out.println(file.exists());
			if (file.exists()) {

				//get the mimetype
				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				if (mimeType == null) {
					//unknown mimetype so set the mimetype to application/octet-stream
					mimeType = "application/octet-stream";
				}

				response.setContentType(mimeType);

				response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

				 //Here we have mentioned it to show as attachment
				 //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				FileCopyUtils.copy(inputStream, response.getOutputStream());

			}
	 }*/
	 
	 @RequestMapping(path = "/findAdditionalUserDetails/{id}", method = RequestMethod.GET)
	    public AdditionalUserDetails findAdditionalUserDetails(@PathVariable("id") long user_id){
	        return additionaluserdetails_service.findAdditionalUserDetailsById(user_id);
	 }
	 
	 @RequestMapping(path = "/updateAddditionalUserDetails/{id}", method = RequestMethod.POST)
		public ModelAndView updateUser(@Valid @ModelAttribute("additional_user_details") AdditionalUserDetails additional_user_details, BindingResult bindingResult,
				 @RequestParam("file") MultipartFile multipartFile,Model model,HttpServletRequest request,HttpSession session,Principal principal,@PathVariable("id") long additionaluserdetails_id) throws IOException {
		 String referer=request.getHeader("Referer");	 
		  ModelAndView mv = new ModelAndView();
		  if(principal!=null) {
				 Authentication a = SecurityContextHolder.getContext().getAuthentication();
				 UserDetails userDetails = (UserDetails) a.getPrincipal();
		  User user1 =  user_service.findUserByEmail(userDetails.getUsername());
		  AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(additionaluserdetails_id);
			if(additional_user_details1.getAdditionaluserdetails_id() <= 0) {
				   bindingResult.rejectValue("email", "error.user", "User does not Exists!");
			}
		    if(bindingResult.hasErrors()) {
			  System.out.println(bindingResult.getAllErrors());
		   mv.setViewName("500");
		  } else if(additional_user_details1.getAdditionaluserdetails_id() > 0){
			  System.out.println("User_id:"+additional_user_details1.getUser().getUser_id());
		   additional_user_details.getUser().setUser_id(additional_user_details.getUser().getUser_id());
		   additional_user_details.setAdditionaluserdetails_id(additional_user_details1.getUser().getUser_id());
		   additional_user_details.getUser().setRoles(additional_user_details1.getUser().getRoles());
		   additional_user_details.getUser().setPermissions(additional_user_details1.getUser().getPermissions());
		   additional_user_details.getUser().setPassword(additional_user_details1.getUser().getPassword());
		   additionaluserdetails_service.updateAdditionalUserDetails(additional_user_details,multipartFile);
		   model.addAttribute("msg", "Details added successfully!");
		   model.addAttribute("user", new User());
		   mv.setViewName("redirect:"+referer);
		  }
		  
		 return mv;
		  }
	     else {
		    mv.setViewName("signin");
	       return mv;
	     }
		}
	 
	 @RequestMapping(path = "/deleteAdditionalUserDetails/{id}", method = RequestMethod.POST)
		public ModelAndView deleteNotification(@PathVariable("id") long additionaluserdetails_id,HttpServletRequest request) {
			AdditionalUserDetails additional_user_details=additionaluserdetails_service.findAdditionalUserDetailsById(additionaluserdetails_id);
			String referer=request.getHeader("Referer");
			ModelAndView mv=new ModelAndView();
			if(additional_user_details!=null) {
			additionaluserdetails_service.deleteAdditionalUserDetails(additional_user_details);
			mv.setViewName("redirect:"+referer);
			}
			return mv;
	 }

}
