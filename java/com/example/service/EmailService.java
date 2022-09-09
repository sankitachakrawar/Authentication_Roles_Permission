package com.example.service;

import com.example.entities.UserEntity;

public interface EmailService {

	public String sendMail(String emailTo,String subject,String text,UserEntity userEntity);

	public void sendSimpleMessage(String email, String string, String url);

	
}
