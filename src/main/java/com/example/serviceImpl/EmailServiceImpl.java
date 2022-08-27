package com.example.serviceImpl;

import java.util.LinkedList;
import java.util.Queue;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.entities.UserEntity;
import com.example.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	

	  @Override
	  public String sendMail(String emailTo, String subject, String text, UserEntity userEntity) {
		  SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		  simpleMailMessage.setFrom("demo83935@gmail.com");
		  simpleMailMessage.setTo(userEntity.getEmail());
		  
		  simpleMailMessage.setSubject("Apply sucessfully");
		  simpleMailMessage.setText("Text demo");
//		Queue<String> queue=new LinkedList<>();
//		queue.add(emailTo);
//		queue.add(subject);
//		queue.add(text);
	  
	  javaMailSender.send(simpleMailMessage); 
	  return "Email Send";
	  }

	  @Override
		public void sendSimpleMessage(String emailTo, String subject, String text) {

			SimpleMailMessage message = new SimpleMailMessage();
			 message.setFrom("demo83935@gmail.com");
			message.setTo(emailTo);
			message.setSubject(subject);
			message.setText(text);
			javaMailSender.send(message);

		}


		
	}
	 
	

