package Controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import Mail.SendMail;
import Model.User;
import Service.ResetPasswordTokenService;
import Service.User_Service;

@RestController
@CrossOrigin
public class Login_Controller {
	
	@Autowired
	 private User_Service user_service;
	
	@Autowired
	 private ResetPasswordTokenService resetpasswordtoken_service;
	
	
	@RequestMapping(value= {"/signin"}, method=RequestMethod.GET)
	 public ModelAndView signin(Model model) {
	  ModelAndView mv = new ModelAndView();
	  User user = new User();
	  model.addAttribute("user", user);
	  mv.setViewName("signin");
	  
	  return mv;
	 }
	 
	 /*@RequestMapping(value= {"/signin"}, method=RequestMethod.POST)
	  public ModelAndView signinUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
	  ModelAndView model = new ModelAndView();
	  System.out.println(user);
	  User userExists =  user_service.findUserByEmail(user.getEmail()); 
	  if(userExists != null) {*/
		  /*user_service.saveUser(user,"ADMIN");
		   model.addObject("msg", "User has been registered successfully!");
		   model.addObject("user", new User());*/
		  /* model.setViewName("admindashboard");
	  }
	  if(bindingResult.hasErrors()) {
	   System.out.println(bindingResult.getAllErrors());
	   System.out.println(new FieldError("user","email","error").getField());
	   model.setViewName("500");
	  } 
	  return model;
	 }*/
	 
	 
	 @RequestMapping(value = {"signin"}, method=RequestMethod.POST)
	    public String setGetLoginPage(@RequestParam(required = true) String failed,
	            String denied, String logout, Model model, Principal principal,
	            HttpSession session) {
		 System.out.println("Principal");
	        if (principal != null) {
	        	return "redirect:/admin/teacherdashboard";
	        }
	        String loginMessage = "";
	        if (failed != null) {
	            loginMessage = "Wrong Credentials";
	            model.addAttribute("error", loginMessage);
	            return "signin";
	        }
	        if (denied != null) {
	            return "error?denied";
	        }
	        if (logout != null) {
	            return "home";
	        }
	        return "redirect:/admin/teacherdashboard";

	    }
	 
	 @RequestMapping(value= {"forgotpassword"}, method=RequestMethod.GET)
	 public ModelAndView signup(Model model) throws AddressException, MessagingException, IOException {
	  ModelAndView mv = new ModelAndView();
	  User user = new User();
	  model.addAttribute("user", user);
	  mv.setViewName("forgotpassword");
	  
	  return mv;
	 }
	 
	 @RequestMapping(value= {"forgotpassword"}, method=RequestMethod.POST)
	 public void forgotpassword(HttpServletRequest request, 
			   @RequestParam("email") String userEmail) {
		 User user = user_service.findUserByEmail(userEmail);
		    /* if (user == null) {
		         throw new UserNotFoundException();
		     }*/
		 System.out.println(userEmail);
		     String token = UUID.randomUUID().toString();
		     resetpasswordtoken_service.createPasswordResetTokenForUser(user, token, LocalDateTime.now());
		     try {
		    	 SendMail mailSender=new SendMail();
				mailSender.sendmail(request.getContextPath(), 
				   request.getLocale(), token, user);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 @PostMapping("/resetPassword")
	 public void savePassword(final Locale locale, HttpServletRequest request) {
		 String token=request.getParameter("token");
         System.out.println(token);
         String password=request.getParameter("password");
         System.out.println(password);
	     String result = resetpasswordtoken_service.validatePasswordResetToken(token);

	     if(result != null) {
	         
	     }

	     User user = resetpasswordtoken_service.getUserByPasswordResetToken(token);
	     System.out.println(user.getUser_id());
	     if(user!=null) {
	    	 user_service.changeUserPassword(user,password,token);
	     } else {
	         
	     }
	 }

	 
	 @GetMapping("/resetPassword")
	 public ModelAndView showChangePasswordPage(Locale locale, Model model, 
	   @RequestParam("token") String token) {
	     String result = resetpasswordtoken_service.validatePasswordResetToken(token);
	     if(result != null) {
	    	 System.out.println("Result:"+result);
	         //String message = messages.getMessage("auth.message." + result, null, locale);
	         return new ModelAndView("redirect:/signin.html?lang=" 
	             + locale.getLanguage() + "&message=");
	     } else {
	    	 ModelAndView mv = new ModelAndView();
	         model.addAttribute("token", token);
	         mv.setViewName("updatepassword");
	         return mv;
	     }
	 }
	 	 
}
