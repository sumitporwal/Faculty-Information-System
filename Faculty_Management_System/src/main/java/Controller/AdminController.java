package Controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import Model.AdditionalUserDetails;
import Model.Bus;
import Model.BusMidStops;
import Model.LeaveApplication;
import Model.Notification;
import Model.User;
import Model.Vacancy;
import Model.VacancyApplicantDetails;
import Service.AdditionalUserDetails_Service;
import Service.Bus_Service;
import Service.LeaveApplication_Service;
import Service.Notification_Service;
import Service.User_Service;
import Service.Vacancy_Applicant_Details_Service;
import Service.Vacancy_Service;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	User_Service user_service;
	
	@Autowired
    AdditionalUserDetails_Service additionaluserdetails_service;
	
	@Autowired
    Notification_Service notification_service;
	
	@Autowired
    Bus_Service bus_service;
	
	@Autowired
    Vacancy_Service vacancy_service;
	
	@Autowired
    LeaveApplication_Service leaveapplication_service;
	
	@Autowired
    Vacancy_Applicant_Details_Service vacancy_applicant_details_service;

   	
	
	@RequestMapping(value= {"/teacherdashboard"}, method=RequestMethod.GET)
	 public ModelAndView teacherDashboard(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
		 if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
		     User user1 = user_service.findUserByEmail(userDetails.getUsername());
		     session.setAttribute("CurrentLoggedInUser", user1);
		     User user = new User();
		     model.addAttribute("user", user);
		     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
		     model.addAttribute("additional_user_details1", additional_user_details1);
		     if(user_service.getUsersByRole("FACULTY")==null || user_service.getUsersByRole("FACULTY").size()==0) {
		        model.addAttribute("users", "No Records Found");
		     }else {
			    model.addAttribute("users",user_service.getUsersByRole("FACULTY"));
			 }
		     mv.setViewName("Admin/teacherdashboard");
		  
		     return mv;
		     }
		      else {
			    mv.setViewName("signin");
		        return mv;
		      }
	}
	
/*	@RequestMapping(value= {"/admindashboard"}, method=RequestMethod.GET)
	 public ModelAndView adminDashboard(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
	 if(principal!=null) {
		 Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) a.getPrincipal();
	     User user1 = user_service.findUserByEmail(userDetails.getUsername());
	     session.setAttribute("CurrentLoggedInUser", user1);
	     User user = new User();
	     model.addAttribute("user", user);
	     if(user_service.getUsersByRole("ADMIN")==null || user_service.getUsersByRole("ADMIN").size()==0) {
	        model.addAttribute("users", "No Records Found");
	     }else {
		    model.addAttribute("users",user_service.getUsersByRole("ADMIN"));
		 }
	     mv.setViewName("Super_Admin/admindashboard");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}*/
	
	@RequestMapping(value= {"/studentdashboard"}, method=RequestMethod.GET)
	 public ModelAndView studentDashboard(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
		 if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
		     User user1 = user_service.findUserByEmail(userDetails.getUsername());
		     session.setAttribute("CurrentLoggedInUser", user1);
		     User user = new User();
		     model.addAttribute("user", user);
		     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
		     model.addAttribute("additional_user_details1", additional_user_details1);
		     if(user_service.getUsersByRole("STUDENT")==null || user_service.getUsersByRole("STUDENT").size()==0) {
		        model.addAttribute("users", "No Records Found");
		     }else {
			    model.addAttribute("users",user_service.getUsersByRole("STUDENT"));
			 }
		     mv.setViewName("Admin/studentdashboard");
		  
		     return mv;
		     }
		      else {
			    mv.setViewName("signin");
		        return mv;
		      }
	 }
	
	@RequestMapping(value= {"/profile"}, method=RequestMethod.GET)
	 public ModelAndView OwnProfile(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
		 if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
		     User user1 = user_service.findUserByEmail(userDetails.getUsername());
		     session.setAttribute("CurrentLoggedInUser", user1);
		     AdditionalUserDetails additional_user_details= new AdditionalUserDetails();
		     model.addAttribute("additional_user_details", additional_user_details);
		     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
		     model.addAttribute("additional_user_details1", additional_user_details1);
		     mv.setViewName("Admin/ownprofile");
		  
		     return mv;
		     }
		      else {
			    mv.setViewName("signin");
		        return mv;
		      }
	 }
	
	@RequestMapping(value= {"/viewprofile/{id}"}, method=RequestMethod.GET)
	 public ModelAndView ViewProfile(Model model,HttpSession session,Principal principal,@PathVariable("id") long user_id) {
		ModelAndView mv = new ModelAndView();
		 if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
		     User user1 = user_service.findUserByEmail(userDetails.getUsername());
		     session.setAttribute("CurrentLoggedInUser", user1);
		     User user2 = user_service.findUserById(user_id);
		     model.addAttribute("user2", user2);
		     AdditionalUserDetails additional_user_details= new AdditionalUserDetails();
		     model.addAttribute("additional_user_details", additional_user_details);
		     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
		     model.addAttribute("additional_user_details1", additional_user_details1);
		     AdditionalUserDetails additional_user_details2=additionaluserdetails_service.findAdditionalUserDetailsById(user2.getUser_id());
		     model.addAttribute("additional_user_details2", additional_user_details2);
		     if(user2.getRoles().getRole_id()==2) {
		    	 mv.setViewName("Admin/ownprofile");
		     }
		     else if(user2.getRoles().getRole_id()==3) {
		    	 mv.setViewName("Common/viewfacultyprofile");
		     }
		     else if(user2.getRoles().getRole_id()==4) {
		    	 mv.setViewName("Common/viewstudentprofile");
		     }	    
		  
		     return mv;
		     }
		      else {
			    mv.setViewName("signin");
		        return mv;
		      }
	 }
	
	@RequestMapping(value= {"/notifications"}, method=RequestMethod.GET)
	 public ModelAndView Notification(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
	 if(principal!=null) {
		 Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) a.getPrincipal();
	     User user1 = user_service.findUserByEmail(userDetails.getUsername());
	     session.setAttribute("CurrentLoggedInUser", user1);
	     Notification notification = new Notification();
	     model.addAttribute("notification", notification);
	     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
	     model.addAttribute("additional_user_details1", additional_user_details1);
	     if(notification_service.getNotificationListByVisibility(user1.getRoles().getRole_name())==null || notification_service.getNotificationListByVisibility(user1.getRoles().getRole_name()).size()==0) {
	        model.addAttribute("notifications", "No Records Found");
	     }else {
		    model.addAttribute("notifications",notification_service.getNotificationList());
		 }
	     mv.setViewName("Admin/notifications");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}

	@RequestMapping(value= {"/bus"}, method=RequestMethod.GET)
	 public ModelAndView Bus(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
		 if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
		     User user1 = user_service.findUserByEmail(userDetails.getUsername());
		     session.setAttribute("CurrentLoggedInUser", user1);
		     Bus bus = new Bus();
		     model.addAttribute("bus", bus);
		     
		     BusMidStops bus_mid_stops=new BusMidStops();
		     model.addAttribute("bus_mid_stops", bus_mid_stops);
		     
		     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
		     model.addAttribute("additional_user_details1", additional_user_details1);
		     if(bus_service.getBusList()==null || bus_service.getBusList().size()==0) {
		        model.addAttribute("buses", "No Records Found");
		     }else {
			    model.addAttribute("buses",bus_service.getBusList());
			 }
	     mv.setViewName("Admin/bus");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}
	
	@RequestMapping(value= {"/leaveapplication"}, method=RequestMethod.GET)
	 public ModelAndView LeaveApplication(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
	 if(principal!=null) {
		 Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) a.getPrincipal();
	     User user1 = user_service.findUserByEmail(userDetails.getUsername());
	     session.setAttribute("CurrentLoggedInUser", user1);
	     LeaveApplication leave_application= new LeaveApplication();
	     model.addAttribute("leave_application", leave_application);
	     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
	     model.addAttribute("additional_user_details1", additional_user_details1);
	     if(leaveapplication_service.getLeaveApplicationListByDepartment(user1.getDepartment())==null || leaveapplication_service.getLeaveApplicationListByDepartment(user1.getDepartment()).size()==0) {
	        model.addAttribute("leave_applications", "No Records Found");
	     }else {
		    model.addAttribute("leave_applications",leaveapplication_service.getLeaveApplicationListByDepartment(user1.getDepartment()));
		 }
	     mv.setViewName("Admin/leaveapplication");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}

	@RequestMapping(value= {"/ownleaveapplication"}, method=RequestMethod.GET)
	 public ModelAndView ownLeaveApplication(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
	 if(principal!=null) {
		 Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) a.getPrincipal();
	     User user1 = user_service.findUserByEmail(userDetails.getUsername());
	     session.setAttribute("CurrentLoggedInUser", user1);
	     LeaveApplication leave_application= new LeaveApplication();
	     model.addAttribute("leave_application", leave_application);
	     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
	     model.addAttribute("additional_user_details1", additional_user_details1);
	     if(leaveapplication_service.getLeaveApplicationListByUser(user1.getUser_id())==null || leaveapplication_service.getLeaveApplicationListByUser(user1.getUser_id()).size()==0) {
	        model.addAttribute("leave_applications", "No Records Found");
	     }else {
		    model.addAttribute("leave_applications",leaveapplication_service.getLeaveApplicationListByUser(user1.getUser_id()));
		 }
	     mv.setViewName("Admin/ownleaveapplication");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}
	
	@RequestMapping(value= {"/vacancy"}, method=RequestMethod.GET)
	 public ModelAndView Vacancy(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
	 if(principal!=null) {
		 Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) a.getPrincipal();
	     User user1 = user_service.findUserByEmail(userDetails.getUsername());
	     session.setAttribute("CurrentLoggedInUser", user1);
	     Vacancy vacancy = new Vacancy();
	     model.addAttribute("vacancy", vacancy);
	     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
	     model.addAttribute("additional_user_details1", additional_user_details1);
	     if(vacancy_service.getVacancyListByVisibility(user1.getRoles().getRole_name())==null || vacancy_service.getVacancyListByVisibility(user1.getRoles().getRole_name()).size()==0) {
	        model.addAttribute("vacancies", "No Records Found");
	     }else {
		    model.addAttribute("vacancies",vacancy_service.getVacancyList());
		 }
	     mv.setViewName("Admin/vacancy");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}
	
	@RequestMapping(value= {"/vacancy/{id}"}, method=RequestMethod.GET)
	 public ModelAndView VacancyDescription(Model model,HttpSession session,Principal principal,@PathVariable("id") long job_id) {
		ModelAndView mv = new ModelAndView();
	 if(principal!=null) {
		 Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) a.getPrincipal();
	     User user1 = user_service.findUserByEmail(userDetails.getUsername());
	     session.setAttribute("CurrentLoggedInUser", user1);
	     Vacancy vacancy = new Vacancy();
	     model.addAttribute("vacancy", vacancy);
	     VacancyApplicantDetails vacancy_applicant_details=new VacancyApplicantDetails();
	     model.addAttribute("vacancy_applicant_details",vacancy_applicant_details);
	     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
	     model.addAttribute("additional_user_details1", additional_user_details1);
	     if(vacancy_service.findVacancyById(job_id)==null) {
	        model.addAttribute("vacancies", "No Records Found");
	     }else {
		    model.addAttribute("vacancies",vacancy_service.findVacancyById(job_id));
		 }
	     mv.setViewName("Admin/vacancyDescription");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}
	
	@RequestMapping(value= {"/postedvacancy"}, method=RequestMethod.GET)
	 public ModelAndView postedVacancy(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
	 if(principal!=null) {
		 Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) a.getPrincipal();
	     User user1 = user_service.findUserByEmail(userDetails.getUsername());
	     session.setAttribute("CurrentLoggedInUser", user1);
	     Vacancy vacancy = new Vacancy();
	     model.addAttribute("vacancy", vacancy);
	     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
	     model.addAttribute("additional_user_details1", additional_user_details1);
	     if(vacancy_service.getVacancyListByUser(user1.getUser_id())==null) {
	        model.addAttribute("vacancies", "No Records Found");
	     }else {
		    model.addAttribute("vacancies",vacancy_service.getVacancyListByUser(user1.getUser_id()));
		 }
	     mv.setViewName("Admin/postedVacancy");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}
	
	@RequestMapping(value= {"vacancyapplicantDetails/{id}"}, method=RequestMethod.GET)
	 public ModelAndView ApplicantDetails(Model model,HttpSession session,Principal principal,@PathVariable("id") long job_id) {
		ModelAndView mv = new ModelAndView();
	 if(principal!=null) {
		 Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) a.getPrincipal();
	     User user1 = user_service.findUserByEmail(userDetails.getUsername());
	     session.setAttribute("CurrentLoggedInUser", user1);
	     Vacancy vacancy = new Vacancy();
	     model.addAttribute("vacancy", vacancy);
	     VacancyApplicantDetails vacancy_applicant_details=new VacancyApplicantDetails();
	     model.addAttribute("vacancy_applicant_details",vacancy_applicant_details);
	     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
	     model.addAttribute("additional_user_details1", additional_user_details1);
	     if(vacancy_applicant_details_service.findVacancyApplicantDetailsById(job_id).getApplicantdetails_id()==0) {
	        model.addAttribute("applicant_details", "No Records Found");
	     }else {
		    model.addAttribute("applicant_details",vacancy_applicant_details_service.findVacancyApplicantDetailsById(job_id));
		 }
	     mv.setViewName("Admin/vacancyapplicantDetails");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}
	
	@RequestMapping(value= {"/referredvacancy"}, method=RequestMethod.GET)
	 public ModelAndView referredVacancy(Model model,HttpSession session,Principal principal) {
		ModelAndView mv = new ModelAndView();
	 if(principal!=null) {
		 Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) a.getPrincipal();
	     User user1 = user_service.findUserByEmail(userDetails.getUsername());
	     session.setAttribute("CurrentLoggedInUser", user1);
	     Vacancy vacancy = new Vacancy();
	     model.addAttribute("vacancy", vacancy);
	     AdditionalUserDetails additional_user_details1=additionaluserdetails_service.findAdditionalUserDetailsById(user1.getUser_id());
	     model.addAttribute("additional_user_details1", additional_user_details1);
	     if(vacancy_applicant_details_service.findVacancyApplicantDetailsByReferrenceId(user1.getUser_id())==null || vacancy_applicant_details_service.findVacancyApplicantDetailsByReferrenceId(user1.getUser_id()).size()==0) {
	        model.addAttribute("applicant_details", "No Records Found");
	     }else {
		    model.addAttribute("applicant_details",vacancy_applicant_details_service.findVacancyApplicantDetailsByReferrenceId(user1.getUser_id()));
		 }
	     mv.setViewName("Admin/referredVacancy");
	  
	     return mv;
	     }
	      else {
		    mv.setViewName("signin");
	        return mv;
	      }
	}

	

}
