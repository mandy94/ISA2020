
package rs.ac.uns.ftn.informatika.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.dto.MessageDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.service.EmailService;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/email", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmailController {

	String ADMIN_MAIL_ADRESS = "enkoder94@gmail.com";
	@Autowired
	private UserService uservice;
	
	@Autowired
	private EmailService eservice;
	
	@PostMapping(value="/send")
	void sendEmail(@RequestBody MessageDTO msg) {
	
		User receiver = uservice.findById(msg.getReceiver());
		eservice.sendEmail(ADMIN_MAIL_ADRESS, receiver.getEmail() , msg);
	}
	
	}