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

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
    	UserEntity userEntity = userRepository.findByEmail(username);
    	return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),userEntity.getPassword(),new ArrayList<>());
    }
	
	
//private ArrayList<SimpleGrantedAuthority> getAuthority(UserEntity userEntity){
//		
//    	ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//    	System.out.println("Authority>>"+authorities);
//
//    	if((userEntity.getId() + "permission") != null) {
//   		
//    		ArrayList<SimpleGrantedAuthority> authorities1=new ArrayList<>();
//    		System.out.println("Authority 1>>"+authorities1);
//    		
//    		ArrayList<String> permissions=roleService.getPermissionByUserId(userEntity.getId());
//    		System.out.println("Permissions>>"+permissions);
//    		
// 
//    		permissions.forEach(permission -> {
//
//    			authorities1.add(new SimpleGrantedAuthority("ROLE_"+permission));
//    	   		System.out.println("Authority 2>>"+authorities1);
//
//			});
//    		authorities=authorities1;
//    		System.out.println("a>>"+authorities);	
//    		
//    	}
//   	
//    	return authorities;
//  	
//  	
//   }
}
