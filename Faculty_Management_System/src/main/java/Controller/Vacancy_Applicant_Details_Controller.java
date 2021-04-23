package Controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
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

import Model.User;
import Model.Vacancy;
import Model.VacancyApplicantDetails;
import Service.Notification_Service;
import Service.User_Service;
import Service.Vacancy_Applicant_Details_Service;
import Service.Vacancy_Service;

@RestController
@CrossOrigin
public class Vacancy_Applicant_Details_Controller {

	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	 private User_Service user_service;
	
	@Autowired
	  private Notification_Service notification_service;
	
	@Autowired
	  private Vacancy_Service vacancy_service;
	
	@Autowired
	Vacancy_Applicant_Details_Service vacancy_applicant_details_service;

	@RequestMapping(value= {"/applyforvacancy/{id}"}, method=RequestMethod.POST)
	 public ModelAndView ApplyForVacancy(@Valid @ModelAttribute("vacancy_applicant_details") VacancyApplicantDetails vacancy_applicant_details, BindingResult bindingResult,
			 @RequestParam("file") MultipartFile multipartFile,Model model,HttpServletRequest request,HttpSession session,Principal principal,@PathVariable("id") long job_id) throws IOException{
	  String referer=request.getHeader("Referer");	 
	  ModelAndView mv = new ModelAndView();
	  if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
	  User user1 =  user_service.findUserByEmail(userDetails.getUsername());
	  Vacancy vacancy= vacancy_service.findVacancyById(job_id);
	  if(bindingResult.hasErrors()) {
		  System.out.println(bindingResult.getAllErrors());
	   mv.setViewName("500");
	  } else {
	   vacancy_applicant_details.setUser(user1);
	   vacancy_applicant_details.setVacancy(vacancy);
	   vacancy_applicant_details_service.addVacancyApplicantDetails(vacancy_applicant_details,multipartFile);
	   model.addAttribute("msg", "Applied successfully!");
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
		 
	 @RequestMapping(path = "/findVacancyApplicantDetails/{id}", method = RequestMethod.GET)
	    public VacancyApplicantDetails findVacancyApplicantDetails(@PathVariable("id") long job_id){
	        return vacancy_applicant_details_service.findVacancyApplicantDetailsById(job_id);
	 }
	 
	 @RequestMapping(path = "/updateApplicantDetails/{id}", method = RequestMethod.POST)
		public ModelAndView updateVacancyApplicantDetails(@Valid @ModelAttribute("vacancy_applicant_details") VacancyApplicantDetails vacancy_applicant_details, BindingResult bindingResult,@PathVariable("id") long applicantdetails_id,HttpServletRequest request,HttpSession session,Principal principal) {
		 String referer=request.getHeader("Referer");
		 String status=request.getParameter("status");
		 System.out.println(status);
		 ModelAndView mv = new ModelAndView();
		 if(principal!=null) {
			 Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 UserDetails userDetails = (UserDetails) a.getPrincipal();
	        User user1 =  user_service.findUserByEmail(userDetails.getUsername());
			VacancyApplicantDetails vacancy_applicant_details1=vacancy_applicant_details_service.findVacancyApplicantDetailsByApplicantId(applicantdetails_id);
			System.out.println(vacancy_applicant_details1.getDocument().getDocument_id());
			if(vacancy_applicant_details1.getApplicantdetails_id() <= 0) {
				   bindingResult.rejectValue("msg" , "Applicant does not Exists!");
				  }
				  if(bindingResult.hasErrors()) {
				    bindingResult.rejectValue("msg", "Applicant does not Exists!");
				  } else if(vacancy_applicant_details1.getApplicantdetails_id() > 0)  {
					  System.out.println(vacancy_applicant_details1.getDocument().getDocument_id());
					   vacancy_applicant_details.setApplicantdetails_id(vacancy_applicant_details1.getApplicantdetails_id());
					   vacancy_applicant_details.setApplicant_name(vacancy_applicant_details1.getApplicant_name());
					   vacancy_applicant_details.setApplicant_email(vacancy_applicant_details1.getApplicant_email());
					   vacancy_applicant_details.setApplicant_phno(vacancy_applicant_details1.getApplicant_phno());
					   vacancy_applicant_details.setApplicant_dob(vacancy_applicant_details1.getApplicant_dob());
					   vacancy_applicant_details.setLinkedin_profile(vacancy_applicant_details1.getLinkedin_profile());
					   vacancy_applicant_details.setAppliedOn(vacancy_applicant_details1.getAppliedOn());
					   vacancy_applicant_details.setUser(vacancy_applicant_details.getUser());
					   vacancy_applicant_details.setStatus(status);
					   vacancy_applicant_details.setVacancy(vacancy_applicant_details1.getVacancy());
					   vacancy_applicant_details.setDocument(vacancy_applicant_details1.getDocument());
			           vacancy_applicant_details_service.updateVacancyApplicantDetails(vacancy_applicant_details);
			           mv.setViewName("redirect:"+ referer);
				  }	
		 }
				 return mv;
	 }
	 
	 @RequestMapping(path = "/deleteVacancyApplicantDetails/{id}", method = RequestMethod.POST)
		public ModelAndView deleteNotification(@PathVariable("id") long applicantdetails_id,HttpServletRequest request) {
			VacancyApplicantDetails vacancy_applicant_details=vacancy_applicant_details_service.findVacancyApplicantDetailsById(applicantdetails_id);
			String referer=request.getHeader("Referer");
			ModelAndView mv=new ModelAndView();
			if(vacancy_applicant_details!=null) {
			vacancy_applicant_details_service.deleteVacancyApplicantDetails(vacancy_applicant_details);
			mv.setViewName("redirect:"+referer);
			}
			return mv;
	 }

}
