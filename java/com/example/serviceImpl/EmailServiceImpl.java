package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.entities.UserEntity;
import com.example.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
    private JmsTemplate jmsTemplate;

	  @Override
	  public String sendMail(String emailTo, String subject, String text, UserEntity userEntity) {
		  SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		  simpleMailMessage.setFrom("demo83935@gmail.com");
		  simpleMailMessage.setTo(userEntity.getEmail());
		  
		  simpleMailMessage.setSubject("Apply sucessfully");
		  simpleMailMessage.setText("Text demo");
		  //queue implementation
		  	jmsTemplate.convertAndSend(simpleMailMessage);
		 //  javaMailSender.send(simpleMailMessage); 
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
	 
	

