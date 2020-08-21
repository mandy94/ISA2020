package rs.ac.uns.ftn.informatika.spring.security;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	public UserService userService;
	
	@Test
	void userLoads() {
		final List<User> allUsers = userService.findAll();
		assertThat(allUsers).isNotEmpty();	
	}

	@Test
	void addUser() {
		User user = new User("test", "pass", "pera", "ilic", 1587496325487l);
		userService.saveUser(user);
		
		assertThat(userService.findByJMBG(1587496325487l))
					.isNotNull();
		
	}
	
	
	

}
