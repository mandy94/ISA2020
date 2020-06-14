package rs.ac.uns.ftn.informatika.spring.security.service;

import rs.ac.uns.ftn.informatika.spring.security.dto.MessageDTO;

public interface EmailService {

	void sendEmail(String sender, String receiver, MessageDTO msg);    
}

