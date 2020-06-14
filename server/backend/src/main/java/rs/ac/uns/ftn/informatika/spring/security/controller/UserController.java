package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/user/pacient/jmbg")
	@PreAuthorize("hasRole('DOCTOR')")
	public List<Long> getPatientsJMBGs() {
		return this.userService.getJMBGs("PACIENT");
	}

	@GetMapping("/user/pacients")
	@PreAuthorize("hasRole('DOCTOR')")
	public List<User> getPatients() {
		return this.userService.getPacients();
	}
	@GetMapping("/user/pacient/{jmbg}")
	@PreAuthorize("hasRole('DOCTOR')")
	public User loadByJMBG(@PathVariable Long jmbg) {
		return this.userService.findByJMBG(jmbg);
	}
	@GetMapping("/users/pending")
	public List<User> getPendingUsers(){
		return this.userService.getPendingUsers();
	}
	@GetMapping("/users/deny")
	public List<User> getDeniedUsers(){
		return this.userService.getDeniedUsers();
	}
	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}
	
}
