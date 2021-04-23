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
import Model.LeaveApplication;
import Model.Notification;
import Model.User;
import Service.LeaveApplication_Service;
import Service.Notification_Service;
import Service.User_Service;

@RestController
@CrossOrigin
public class LeaveApplicationController {

	@Autowired
	 private User_Service user_service;
	
	@Autowired
	  private LeaveApplication_Service leaveapplication_service;

	@RequestMapping(value= {"/applyforleave"}, method=RequestMethod.POST)
	 public ModelAndView applyForLeave(@Valid @ModelAttribute("leave_application") LeaveApplication leave_application, BindingResult bindingResult,
			 Model model,HttpServletRequest request,HttpSession session,Principal principal) throws IOException{
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
	   System.out.println(leave_application.getLeaveapplication_id());
	   leave_application.setUser(user1);
	   leaveapplication_service.applyForLeave(leave_application);;
	   model.addAttribute("msg", "Applied for Leave successfully!");
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
		 
	 @RequestMapping(path = "/findLeaveApplication/{id}", method = RequestMethod.GET)
	    public LeaveApplication findLeaveApplication(@PathVariable("id") long leaveapplication_id){
	        return leaveapplication_service.findLeaveApplicationById(leaveapplication_id);
	 }
	 
	 @RequestMapping(path = "/updateLeaveApplication/{id}", method = RequestMethod.POST)
		public ModelAndView updateUser(@Valid @ModelAttribute("leave_application") LeaveApplication leave_application, BindingResult bindingResult,@PathVariable("id") long leaveapplication_id,HttpServletRequest request,HttpSession session,Principal principal) {
		 String referer=request.getHeader("Referer");
		 String status=request.getParameter("status");
		 System.out.println(status);
		 ModelAndView mv = new ModelAndView();
		 if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
	        User user1 =  user_service.findUserByEmail(userDetails.getUsername());
			LeaveApplication leave_application1=leaveapplication_service.findLeaveApplicationById(leaveapplication_id);
			if(leave_application1.getLeaveapplication_id() <= 0) {
				   bindingResult.rejectValue("msg" , "Leave Application Exists!");
				  }
				  if(bindingResult.hasErrors()) {
				    bindingResult.rejectValue("msg", "Leave Application does not Exists!");
				  } else if(leave_application1.getLeaveapplication_id() > 0)  {
					   leave_application.setLeaveapplication_id(leave_application1.getLeaveapplication_id());
					   leave_application.setUser(leave_application1.getUser());
					   leave_application.setStatus(status);
					   leave_application.setLeave_approver(user1.getName());
			           leaveapplication_service.updateLeaveApplication(leave_application);
			           mv.setViewName("redirect:"+ referer);
				  }	
		 }
				 return mv;
	 }
	 
	 @RequestMapping(path = "/deleteleave_application/{id}", method = RequestMethod.POST)
		public ModelAndView deleteLeaveApplication(@PathVariable("id") long leaveapplication_id,HttpServletRequest request) {
			LeaveApplication leave_application=leaveapplication_service.findLeaveApplicationById(leaveapplication_id);
			String referer=request.getHeader("Referer");
			ModelAndView mv=new ModelAndView();
			if(leave_application!=null) {
			leaveapplication_service.deleteLeaveApplication(leave_application);
			mv.setViewName("redirect:"+referer);
			}
			return mv;
	 }

}
