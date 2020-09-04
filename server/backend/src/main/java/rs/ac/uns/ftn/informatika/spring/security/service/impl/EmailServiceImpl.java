package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.dto.MessageDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.MailMail;
import rs.ac.uns.ftn.informatika.spring.security.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public void sendEmail(String sender, String receiver, MessageDTO msg) {
		@SuppressWarnings("resource")
		ApplicationContext res = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
		
		MailMail ms = (MailMail)res.getBean("mailMail");
		
		ms.sendMail(sender, receiver , msg.getTitle() , msg.getContent());  
	      
		System.out.println("Send email  - success");  	
	
		
	}

	@Override
	public String getMainAdminAdress() {
		return  "enkoder94@gmail.com";
	}
}