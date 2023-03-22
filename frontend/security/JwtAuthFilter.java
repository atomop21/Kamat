package com.example.cafe.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			System.out.println(authHeader);

			jwtToken=authHeader.substring(7);
			try {
				username=this.jwtTokenHelper.getUsernameFromToken(jwtToken);
			}catch(IllegalArgumentException iae) {
				System.out.println("unable to get jwt token");
			}catch(ExpiredJwtException eje) {
				System.out.println("Token Expired");
			}
		}else {
			logger.warn("token does not begin with bearer");
			System.out.println(authHeader);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=this.customUserDetailService.loadUserByUsername(username);
			
			if(jwtTokenHelper.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
}

		

}

