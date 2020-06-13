package rs.ac.uns.ftn.informatika.spring.security.model;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import rs.ac.uns.ftn.informatika.spring.security.dto.MessageDTO;  

public class MailMail{  
 private MailSender mailSender;  
   
    public void setMailSender(MailSender mailSender) {  
        this.mailSender = mailSender;  
    }  

    public void sendMail(String sender, String receiver, String title, String content) {  
       
        SimpleMailMessage message = new SimpleMailMessage();  
        message.setFrom(sender);  
        message.setTo(receiver);  
        message.setSubject(title);  
        message.setText(content);  
       
        mailSender.send(message);     
    }  
}  