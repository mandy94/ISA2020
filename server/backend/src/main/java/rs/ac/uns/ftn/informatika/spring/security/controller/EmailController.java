
package rs.ac.uns.ftn.informatika.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.dto.MessageDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.MailMail;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/email", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmailController {

	@Autowired
	private UserService uservice;
	@PostMapping(value="/send")
	void sendEmail(@RequestBody MessageDTO msg) {
	
		User receiver = uservice.findById(msg.getReceiver());
//		User sender = uservice.findById(msg.getSender());
		@SuppressWarnings("resource")
		ApplicationContext res = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
		
		MailMail ms = (MailMail)res.getBean("mailMail");
		
		ms.sendMail("enkoder94@gmail.com", receiver.getEmail() , msg.getTitle() , msg.getContent());  
	      
		System.out.println("Send email  - success");  	
		}
	
	}