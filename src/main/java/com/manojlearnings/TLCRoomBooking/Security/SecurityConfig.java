package com.manojlearnings.TLCRoomBooking.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.manojlearnings.TLCRoomBooking.Service.CustomUserDetailsServices;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	CustomUserDetailsServices customUserDetailsServices;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
			@SuppressWarnings("removal")
			@Bean
			public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
				
				http.csrf().disable().authorizeRequests()
				.requestMatchers("/register","/reset-password", "/password-request").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/home", true).permitAll()
				.and()
				.logout()
				.invalidateHttpSession(true)
			     .clearAuthentication(true)
			     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			     .logoutSuccessUrl("/login?logout").permitAll();
				
				return http.build();
				
		}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsServices).passwordEncoder(passwordEncoder());
	}

}
