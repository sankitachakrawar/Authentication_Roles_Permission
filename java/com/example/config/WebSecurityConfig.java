package com.example.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.example.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter filter;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	  @Override	  
	  @Bean public UserDetailsService userDetailsService() {
	  
	  return super.userDetailsService();
	  
	  }
	
	 @Override 
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  
		  auth.userDetailsService(customUserDetailsService);
	  }
	 @Override
	    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

		
		  @Override 
		  protected void configure(HttpSecurity http) throws Exception {
			  http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
				.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/auth/register","/auth/login","/api/forgot-pass-confirm","/auth/forgot-pass","/api/orders","/api/success").permitAll().
				//antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll().
				anyRequest().authenticated().and().httpBasic().and().
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		  
		  }
	  
			@Bean
			public CorsConfigurationSource corsConfigurationSource() {

				CorsConfiguration configuration = new CorsConfiguration();
				configuration.setAllowedOrigins(Arrays.asList("*"));
				configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
				configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
				configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
				UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
				source.registerCorsConfiguration("/**", configuration);
				return source;

			}
			
			 @Bean
			  public PasswordEncoder passwordEncoder() {
				  return new BCryptPasswordEncoder();	  
			  }
			 
}
