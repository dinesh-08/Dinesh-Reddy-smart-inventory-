package com.mybootapp.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mybootapp.main.service.UserService;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userservice;
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// TODO Auto-generated method stub
	 auth.inMemoryAuthentication()
	 .withUser("kvdinesh2004.com").password(getEncoder().encode("kvd")).authorities("ADMIN")
	 .and()
	 .withUser("kvlokesh2004.com").password(getEncoder().encode("kvl")).authorities("USER");
//	 auth.authenticationProvider(getProvider());
	
}

private AuthenticationProvider getProvider() {
	DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
	dao.setPasswordEncoder(getEncoder());
	
	dao.setUserDetailsService(userservice);
	return dao;
}
@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/inwardregister/add/{productId}/{godownId}/{supplierId}").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.POST, "/customer/add").permitAll()
		 
		 .antMatchers(HttpMethod.POST, "/supplier/add").authenticated()
		 .antMatchers(HttpMethod.POST, "/admin/add").permitAll()
		 .antMatchers(HttpMethod.POST, "/manager/add").hasAuthority("ADMIN")
		 .antMatchers(HttpMethod.POST, "/employee/add/{managerId}").hasAuthority("ADMIN")
		 .antMatchers(HttpMethod.GET, "/inwardregister/report").hasAuthority("ADMIN")
		 .antMatchers(HttpMethod.GET,"/outwardregister/report").hasAuthority("ADMIN")
		 .antMatchers(HttpMethod.GET,"/godown/report").hasAuthority("ADMIN")
		 .anyRequest().permitAll()
		 .and()
		 .httpBasic()
		 .and()
		 .csrf().disable();
	}
@Bean
public PasswordEncoder getEncoder()
{
	return new BCryptPasswordEncoder();
}


}

