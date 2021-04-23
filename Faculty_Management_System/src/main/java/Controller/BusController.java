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

import Model.Bus;
import Model.BusMidStops;
import Model.Document;
import Model.Notification;
import Model.User;
import Service.Bus_Service;
import Service.Notification_Service;
import Service.User_Service;

@RestController
@CrossOrigin
public class BusController {
	
	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Autowired
	 private User_Service user_service;
	
	@Autowired
	  private Bus_Service bus_service;

	@RequestMapping(value= {"/addbus"}, method=RequestMethod.POST)
	 public ModelAndView addBus(@Valid @ModelAttribute("bus") Bus bus, BindingResult bindingResult,
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
	   bus.setUser(user1);
	   bus_service.addBus(bus);
	   model.addAttribute("msg", "Bus added successfully!");
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
		 
	 @RequestMapping(path = "/findBus/{id}", method = RequestMethod.GET)
	    public Bus findBus(@PathVariable("id") long bus_id){
	        return bus_service.findBusById(bus_id);
	 }
	 
	 @RequestMapping(path = "/updatebus/{id}", method = RequestMethod.POST)
		public ModelAndView updateUser(@Valid @ModelAttribute("bus") Bus bus, BindingResult bindingResult,@PathVariable("id") long bus_id,HttpServletRequest request) {
		 String referer=request.getHeader("Referer");
		 ModelAndView mv = new ModelAndView();
			Bus bus1=bus_service.findBusById(bus_id);
			if(bus1.getBus_id() <= 0) {
				   bindingResult.rejectValue("msg" , "Bus Exists!");
				  }
				  if(bindingResult.hasErrors()) {
				    bindingResult.rejectValue("msg", "Bus does not Exists!");
				  } else if(bus1.getBus_id() > 0)  {
					  System.out.println(bus);
					   bus.setBus_id(bus1.getBus_id());
			           bus_service.updateBus(bus);
			           mv.setViewName("redirect:"+ referer);
				  }	
				 return mv;
	 }
	 
	 @RequestMapping(path = "/deletebus/{id}", method = RequestMethod.POST)
		public ModelAndView deleteBus(@PathVariable("id") long bus_id,HttpServletRequest request) {
			Bus bus=bus_service.findBusById(bus_id);
			String referer=request.getHeader("Referer");
			ModelAndView mv=new ModelAndView();
			if(bus!=null) {
			bus_service.deleteBus(bus);
			mv.setViewName("redirect:"+referer);
			}
			return mv;
	 }

	 @RequestMapping(value= {"/addbusstops/{id}"}, method=RequestMethod.POST)
	 public ModelAndView addBusStops(@Valid @ModelAttribute("bus_mid_stops") BusMidStops bus_mid_stops, BindingResult bindingResult,
			 @PathVariable("id") long bus_id,Model model,HttpServletRequest request,HttpSession session,Principal principal) throws IOException{
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
	   Bus bus=bus_service.findBusById(bus_id);
	   bus_mid_stops.setBus(bus);
	   bus_service.addBusStops(bus_mid_stops);
	   model.addAttribute("msg", "Bus added successfully!");
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
	 
	 @RequestMapping(value= {"/viewbusstops/{id}"}, method=RequestMethod.GET)
	 public List<BusMidStops> ViewBusStops(@Valid @ModelAttribute("bus_mid_stops") BusMidStops bus_mid_stops, BindingResult bindingResult,
			 @PathVariable("id") long bus_id,Model model,HttpServletRequest request,HttpSession session,Principal principal) throws IOException{
		return  bus_service.getBusStopsList(bus_id);
	     }
		 
	 @RequestMapping(path = "/findBusStops/{id}", method = RequestMethod.GET)
	    public BusMidStops findBusStops(@PathVariable("id") long stop_id){
	        return bus_service.findBusStopsById(stop_id);
	 }
	 
	 @RequestMapping(path = "/updatebusstops/{id}", method = RequestMethod.POST)
		public ModelAndView updateBusStops(@Valid @ModelAttribute("bus_mid_stops") BusMidStops bus_mid_stops, BindingResult bindingResult,@PathVariable("id") long stop_id,HttpServletRequest request) {
		 String referer=request.getHeader("Referer");
		 ModelAndView mv = new ModelAndView();
			BusMidStops bus_mid_stops1=bus_service.findBusStopsById(stop_id);
			if(bus_mid_stops1.getStop_id() <= 0) {
				   bindingResult.rejectValue("msg" , "Bus Stop Exists!");
				  }
				  if(bindingResult.hasErrors()) {
				    bindingResult.rejectValue("msg", "Bus Stop does not Exists!");
				  } else if(bus_mid_stops1.getStop_id() > 0)  {
					   bus_mid_stops.setStop_id(bus_mid_stops1.getStop_id());
					   bus_mid_stops.setStop_name(request.getParameter("bus_mid_stops[stop_name]"));
					   bus_mid_stops.setStop_time(request.getParameter("bus_mid_stops[stop_time]"));
					   bus_mid_stops.setBus(bus_mid_stops1.getBus());
			           bus_service.updateBusStops(bus_mid_stops);
			           mv.setViewName("redirect:"+ referer);
				  }	
				 return mv;
	 }
	 
	 @RequestMapping(path = "/deletebusstops/{id}", method = RequestMethod.POST)
		public ModelAndView deleteBusStops(@PathVariable("id") long stop_id,HttpServletRequest request) {
			BusMidStops bus_mid_stops=bus_service.findBusStopsById(stop_id);
			String referer=request.getHeader("Referer");
			ModelAndView mv=new ModelAndView();
			if(bus_mid_stops!=null) {
			bus_service.deleteBusStops(bus_mid_stops);
			mv.setViewName("redirect:"+referer);
			}
			return mv;
	 }

}
