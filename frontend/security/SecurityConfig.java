package com.example.cafe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	private CustomUserDetailService cusUsDet;
	
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		.cors(Customizer.withDefaults())
		.authorizeHttpRequests().requestMatchers("/kamat/auth/login",
				"/kamat/register",
				"/kamat/addtocart",
				"/kamat/remcart/{pid}",
				"/kamat/prod/image/{imgname}",
				"/kamat/user/profile/{profilepic}",
				"/kamat/logo/{logoname}",
				"/kamat/products",
				"/kamat/sendotp").permitAll()		
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
		.and()
		.authenticationProvider(daoAuthProv())
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		DefaultSecurityFilterChain bui= http.build();
		return bui;
		
	}
	
	@Bean
	public PasswordEncoder passEnc() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception  {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider daoAuthProv() {
		DaoAuthenticationProvider provid=new DaoAuthenticationProvider();
		provid.setUserDetailsService(this.cusUsDet);
		provid.setPasswordEncoder(passEnc());
		return provid;
	}
	
	
}
