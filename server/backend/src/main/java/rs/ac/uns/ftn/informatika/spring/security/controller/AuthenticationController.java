package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rs.ac.uns.ftn.informatika.spring.security.dto.MessageDTO;
import rs.ac.uns.ftn.informatika.spring.security.exception.ResourceConflictException;
import rs.ac.uns.ftn.informatika.spring.security.model.AdminResponse;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.model.UserRequest;
import rs.ac.uns.ftn.informatika.spring.security.model.UserTokenState;
import rs.ac.uns.ftn.informatika.spring.security.security.TokenUtils;
import rs.ac.uns.ftn.informatika.spring.security.security.auth.JwtAuthenticationRequest;
import rs.ac.uns.ftn.informatika.spring.security.service.EmailService;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;
import rs.ac.uns.ftn.informatika.spring.security.service.impl.CustomUserDetailsService;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private EmailService eservice;
	
	@Autowired
	private UserService userService;

	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {

		// 
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	
	private String linkForAllowingRegistration = "http://localhost:4200/";
	// Endpoint za registraciju novog korisnika
	@PostMapping("/signup")
	public ResponseEntity<User> registerUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {

		User existUser = this.userService.findByUsername(userRequest.getUsername());
		if (existUser != null) {
			throw new ResourceConflictException(userRequest.getId(), "Username already exists");
		}
		
		
		User user = this.userService.save(userRequest);
		eservice.sendEmail(user.getEmail(), 
				eservice.getMainAdminAdress(),
				new MessageDTO("Zahtev za registraciju" , 
				"Novi korisnik " + user.getEmail() + " zahteva da se registruje. Ako zelite da ga prihvatite kliknite na sledeci link: "
						 + linkForAllowingRegistration  +" ako zelite da odbijte, kliknite na ovaj <a href=''> link </a>"));
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<>(user, HttpStatus.CREATED);
 
	}
	@GetMapping(value="/allow/{username}", consumes = "application/json")
	public List<User> allowRegistration(@PathVariable String username) {
		User usr = userService.findByUsername(username);
		usr.setStatus("ACTIVE");
		usr.setEnabled(true);
		
		userService.saveUser(usr); 
		return userService.getPendingUsers();
	}
	
	@PutMapping(value="/deny/{username}", consumes = "application/json")
	public List<User> denyRegistration(@PathVariable String username, @RequestBody AdminResponse response) {
		User usr = userService.findByUsername(username);
		usr.setStatus("DENIED");
		usr.setRegistrationResponse(response.getDescripton());
		MessageDTO msg = new MessageDTO();
		msg.setTitle("Razlog odbijanja registracije");
		msg.setContent(response.getDescripton());
		
		eservice.sendEmail(eservice.getMainAdminAdress(), response.getEmail(), msg );
		userService.saveUser(usr); 
		return userService.getPendingUsers();
	}
	
	
	
	
	
	
	
	// U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token osvezi
	@PostMapping(value = "/refresh")
	public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
}
