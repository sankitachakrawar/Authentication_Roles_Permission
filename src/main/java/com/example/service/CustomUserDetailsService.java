package com.example.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.entities.UserEntity;
import com.example.repository.UserRepository;
import com.example.utils.CacheOperation;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CacheOperation cache;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
 //   UserEntity userEntity = userRepository.findByEmail(username);
 //   System.out.println(userEntity.getUsername()+" "+userEntity.getPassword()+" "+getAuthority(userEntity));
 //   	return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),userEntity.getPassword(),getAuthority(userEntity));
		
		
		UserEntity user;
		
		if(!cache.isKeyExist(username, username)) {
			
			user = userRepository.findByEmail(username);
			//System.out.println("user>>  "+user);
			cache.addInCache(username, username, user);
		}
		else {
			user =  (UserEntity) cache.getFromCache(username, username);
		}
		if(user ==  null) {
			throw new UsernameNotFoundException("User not found with Username: " +username);
		}
		System.out.println(user.getUsername()+"    "+user.getPassword()+"   "+getAuthority(user));
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthority(user));
		
		
		
    }
	
	
	private ArrayList<SimpleGrantedAuthority> getAuthority(UserEntity userEntity){
		
    	ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

    	if (!cache.isKeyExist(userEntity.getId() + "permission", userEntity.getId() + "permission")) {
    	//if((userEntity.getId() + "permission") != null) {
    		System.out.println(userEntity.getId()+ "permission");
    		ArrayList<SimpleGrantedAuthority> authorities1=new ArrayList<>();
    	
    		ArrayList<String> permissions=roleService.getPermissionByUserId(userEntity.getId());
    	
    		permissions.forEach(permission -> {

    			authorities1.add(new SimpleGrantedAuthority("ROLE_"+permission));
    	   		
    	});
    
    				
    		authorities=authorities1;  
    		cache.addInCache(userEntity.getId() + "permission", userEntity.getId() + "permission", authorities1);
    	}
    		      else {
			authorities = (ArrayList<SimpleGrantedAuthority>) cache.getFromCache(userEntity.getId() + "permission", userEntity.getId() + "permission");

		}	
    	return authorities;
   }
}
