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

import Model.Document;
import Model.Notification;
import Model.User;
import Service.Notification_Service;
import Service.User_Service;

@RestController
@CrossOrigin
public class NotificationController {
	
	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	 private User_Service user_service;
	
	@Autowired
	  private Notification_Service notification_service;

	@RequestMapping(value= {"/generatenotification"}, method=RequestMethod.POST)
	 public ModelAndView generateNotification(@Valid @ModelAttribute("notification") Notification notification, BindingResult bindingResult,
			 @RequestParam("file") MultipartFile multipartFile,Model model,HttpServletRequest request,HttpSession session,Principal principal) throws IOException{
	  String referer=request.getHeader("Referer");	 
	  ModelAndView mv = new ModelAndView();
	  if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
	  User user1 =  user_service.findUserByEmail(userDetails.getUsername());
	  if(bindingResult.hasErrors()) {
		  System.out.println(bindingResult.getAllErrors());
	   mv.setViewName("500");
	  } else {
	   notification.setUser(user1);
	   notification_service.addNotification(notification,multipartFile);
	   model.addAttribute("msg", "Notification added successfully!");
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
	
	 @RequestMapping(path = "/media/download/{fileName:.+}", method = RequestMethod.GET)
	 public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
				@PathVariable("fileName") String fileName) throws IOException {
		    
		    Document document=notification_service.findByDocumentName(fileName);
        
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

				/**
				 * In a regular HTTP response, the Content-Disposition response header is a
				 * header indicating if the content is expected to be displayed inline in the
				 * browser, that is, as a Web page or as part of a Web page, or as an
				 * attachment, that is downloaded and saved locally.
				 * 
				 */

				/**
				 * Here we have mentioned it to show inline
				 */
				response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

				 //Here we have mentioned it to show as attachment
				 //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				FileCopyUtils.copy(inputStream, response.getOutputStream());

			}
	 }
	 
	 @RequestMapping(path = "/findNotification/{id}", method = RequestMethod.GET)
	    public Notification findNotification(@PathVariable("id") long notification_id){
	        return notification_service.findNotificationById(notification_id);
	 }
	 
	 @RequestMapping(path = "/updatenotification/{id}", method = RequestMethod.POST)
		public ModelAndView updateUser(@Valid @ModelAttribute("notification") Notification notification, BindingResult bindingResult,@PathVariable("id") long notification_id,HttpServletRequest request) {
		 String referer=request.getHeader("Referer");
		 ModelAndView mv = new ModelAndView();
			Notification notification1=notification_service.findNotificationById(notification_id);
			if(notification1.getNotification_id() <= 0) {
				   bindingResult.rejectValue("msg" , "Notification Exists!");
				  }
				  if(bindingResult.hasErrors()) {
				    bindingResult.rejectValue("msg", "Notification does not Exists!");
				  } else if(notification1.getNotification_id() > 0)  {
					  System.out.println(notification);
					   notification.setNotification_id(notification1.getNotification_id());
					   //notification.setDocument(notification1.getDocument());
			           notification_service.updateNotification(notification);
			           mv.setViewName("redirect:"+ referer);
				  }	
				 return mv;
	 }
	 
	 @RequestMapping(path = "/deletenotification/{id}", method = RequestMethod.POST)
		public ModelAndView deleteNotification(@PathVariable("id") long notification_id,HttpServletRequest request) {
			Notification notification=notification_service.findNotificationById(notification_id);
			String referer=request.getHeader("Referer");
			ModelAndView mv=new ModelAndView();
			if(notification!=null) {
			notification_service.deleteNotification(notification);
			mv.setViewName("redirect:"+referer);
			}
			return mv;
	 }

}
