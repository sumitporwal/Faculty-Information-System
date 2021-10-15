package com.example.Faculty_Management_System;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import DAO.AdditionalUserDetails_DAO;
import Model.AdditionalUserDetails;
import Model.Permission;
import Model.Role;
import Model.User;
import Service.Role_Service;
import Service.User_Service;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	 private DataSource dataSource;
	
	@Autowired
	private User_Service userService;
	
	@Autowired
	private Role_Service roleService;
	
	@Autowired
	private AdditionalUserDetails_DAO additional_user_details_dao;
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        System.out.println(authProvider);
         
        return authProvider;
    }
			
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//super.configure(http);
		  http.authorizeRequests()
		   .antMatchers("/js/**", "/css/**","/images/**","/img/**","/vendor/**","/fonts/**","/uploads/**","/favicon.ico/**").permitAll()
		   .antMatchers("/").permitAll()
		   .antMatchers("/signupAdmin").permitAll()
		   .antMatchers("/signup").permitAll()
		   .antMatchers("/signupTeacher").permitAll()
		   .antMatchers("/signin").permitAll()
		   .antMatchers("/forgotpassword").permitAll()
		   .antMatchers("/resetPassword").permitAll()
		   .antMatchers("/superadmin/findUser/{id}").permitAll()
		   .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
		   .authenticated().and().csrf().disable()
		   .formLogin().loginPage("/signin").failureUrl("/signin?error=true")
		   .defaultSuccessUrl("/custom")
		   .usernameParameter("email")
		   .passwordParameter("password")
		   .and().logout()
		   .invalidateHttpSession(true)
		   .clearAuthentication(true)
		   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		   .logoutSuccessUrl("/signin")
		   .and().rememberMe()
		    .key("uniqueAndSecret")
		        .userDetailsService(userService)
		   /*.tokenRepository(persistentTokenRepository())
		   .tokenValiditySeconds(60*60)*/
		   .and().exceptionHandling().accessDeniedPage("/403");
		  
		  http.sessionManagement().maximumSessions(1).expiredUrl("/signin?expired=true");

		 }
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.authenticationProvider(authenticationProvider());
	}
	
	@PostConstruct
	protected void saveSuperUser() {
		User user=new User();
		Role role=new Role();
		Permission permissions = new Permission();
		AdditionalUserDetails additional_user_details= new AdditionalUserDetails();
		role.setRole_name("SUPER_ADMIN");
		roleService.addRole(role);
		user.setName("Super admin");
		user.setEmail("superadmin@gmail.com");
		user.setEnabled(true);
		user.setPassword("Super@1289");
		user.setUsername("SuperAdmin88");
		user.setRoles(role);
		user.setPermissions(permissions);
	//	user.setAdditional_user_details(additional_user_details);
		userService.saveUser(user, role.getRole_name());
	}

   /* @Override
    public void configure(WebSecurity web) throws Exception{
    	web
    	   .ignoring()
    	      .antMatchers("/static/**");
    }*/
	

	/*@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		  JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		  db.setDataSource(dataSource);
		  
		  return db;
		 }*/
}