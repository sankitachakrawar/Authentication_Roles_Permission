package com.example.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.service.CustomUserDetailsService;
import com.example.utils.JwtTokenUtil;


@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenUtil jwtToUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token=null;
		String email=null;
		String authorizationHeader=request.getHeader("Authorization");
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token=authorizationHeader.substring(7);
			email=jwtToUtil.getEmailFromToken(token);
		}
		
		if(email != null && SecurityContextHolder.getContext().getAuthentication()== null) {
		
			UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(email);
			
			System.out.println("GET EMAIL: "+ userDetails);
			
			if(jwtToUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
