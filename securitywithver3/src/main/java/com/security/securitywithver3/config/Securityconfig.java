package com.security.securitywithver3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Securityconfig {
	
	
	//authentication:users can be checked here
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		
		UserDetails user1=User.withUsername("rishu").password(encoder.encode("rishu123"))
				          .roles("USER").build();
		UserDetails admin=User.withUsername("ranjeet").password(encoder.encode("raju123"))
				          .roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user1,admin);
	}
	
	////authorization: give permission to use/access the resources(resources means method)
	
	@Bean
	public SecurityFilterChain getSecurity(HttpSecurity http) throws Exception {
		    http.csrf(csrf->csrf.disable())
		      .authorizeHttpRequests(auth->auth.requestMatchers("/products/welcome").permitAll())
		      .authorizeHttpRequests(auth->auth.requestMatchers("products/**").authenticated()
		    		  ).formLogin(withDefault());
		    
		    return http.build();
		
	}
	

	@Bean
	public Customizer<FormLoginConfigurer<HttpSecurity>> withDefault() {
		// TODO Auto-generated method stub
		return new Customizer<FormLoginConfigurer<HttpSecurity>>() {
			
			@Override
			public void customize(FormLoginConfigurer<HttpSecurity> t) {
				// TODO Auto-generated method stub
				
			}
		};
	}
   //getting bean of password encoder
	@Bean
	public PasswordEncoder getEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
