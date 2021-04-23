package Controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.util.List;

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

import DAO.Vacancy_DAO;
import Model.Document;
import Model.Notification;
import Model.User;
import Model.Vacancy;
import Service.Notification_Service;
import Service.User_Service;
import Service.Vacancy_Service;

@RestController
@CrossOrigin
public class VacancyController {

	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	 private User_Service user_service;
	
	@Autowired
	  private Notification_Service notification_service;
	
	@Autowired
	Vacancy_Service vacancy_service;

	@RequestMapping(value= {"/generatevacancy"}, method=RequestMethod.POST)
	 public ModelAndView generateVacancy(@Valid @ModelAttribute("vacancy") Vacancy vacancy, BindingResult bindingResult,
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
	   vacancy.setUser(user1);
	   vacancy_service.addVacancy(vacancy,multipartFile);
	   model.addAttribute("msg", "Vacancy added successfully!");
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
		 
	 @RequestMapping(path = "/findVacancy/{id}", method = RequestMethod.GET)
	    public Vacancy findVacancy(@PathVariable("id") long job_id){
	        return vacancy_service.findVacancyById(job_id);
	 }
	 
	 @RequestMapping(path = "/updatevacancy/{id}", method = RequestMethod.POST)
		public ModelAndView updateVacancy(@Valid @ModelAttribute("vacancy") Vacancy vacancy, BindingResult bindingResult,@PathVariable("id") long job_id,HttpServletRequest request) {
		 String referer=request.getHeader("Referer");
		 ModelAndView mv = new ModelAndView();
			Vacancy vacancy1=vacancy_service.findVacancyById(job_id);
			if(vacancy1.getJob_id() <= 0) {
				   bindingResult.rejectValue("msg" , "Vacancy Exists!");
				  }
				  if(bindingResult.hasErrors()) {
				    bindingResult.rejectValue("msg", "Vacancy does not Exists!");
				  } else if(vacancy1.getJob_id() > 0)  {
					  System.out.println(vacancy);
					   vacancy.setJob_id(vacancy1.getJob_id());
					   //notification.setDocument(notification1.getDocument());
			           vacancy_service.updateVacancy(vacancy);
			           mv.setViewName("redirect:"+ referer);
				  }	
				 return mv;
	 }
	 
	 @RequestMapping(path = "/deletevacancy/{id}", method = RequestMethod.POST)
		public ModelAndView deleteNotification(@PathVariable("id") long job_id,HttpServletRequest request) {
			Vacancy vacancy=vacancy_service.findVacancyById(job_id);
			String referer=request.getHeader("Referer");
			ModelAndView mv=new ModelAndView();
			if(vacancy!=null) {
			vacancy_service.deleteVacancy(vacancy);
			mv.setViewName("redirect:"+referer);
			}
			return mv;
	 }
	 
	 @RequestMapping(path = "/PostedVacancy/{id}", method = RequestMethod.GET)
	    public List<Vacancy> PostedVacancy(@PathVariable("id") long user_id){
	        return vacancy_service.getVacancyListByUser(user_id);
	 }


}
