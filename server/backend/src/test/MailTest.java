package rs.ac.uns.ftn.informatika.spring.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailTest {   

@Test
	void first{
	      
	Resource r = new ClassPathResource("applicationContext.xml");  
	BeanFactory b = new XmlBeanFactory(r);  
	MailMail m=(MailMail)b.getBean("mailMail");  
	String sender="sendergmailid@gmail.com";//write here sender gmail id  
	String receiver="receiveremailid@gmail.com";//write here receiver id  
	m.sendMail(sender,receiver,"hi","welcome");  
	      
	System.out.println("success");  
}  
}  