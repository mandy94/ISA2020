package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.Authority;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.model.UserRequest;
import rs.ac.uns.ftn.informatika.spring.security.repository.UserRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.AppointmentService;
import rs.ac.uns.ftn.informatika.spring.security.service.AuthorityService;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;


	@Autowired
	private AuthorityService authService;
	
	@Autowired
	private AppointmentService appservice;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username);
		return u;
	}

	public User findById(Long id) throws AccessDeniedException {
		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}

	public List<User> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		return result;
	}


	@Override
	@Transactional
	public User save(UserRequest userRequest) {
		User u = new User();
		u.setUsername(userRequest.getUsername());
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		u.setFirstName(userRequest.getFirstname());
		u.setLastName(userRequest.getLastname());
		u.setEmail(userRequest.getEmail());
		u.setEnabled(false);
		u.setStatus("PENDING");
		u.setDoIHaveToChangePassword(true);
		if(userRequest.getRole().equals("Administrator"))
			u.setRole("ADMIN");
		if(userRequest.getRole().equals("Korisnik"))
			u.setRole("PACIENT");
		if(userRequest.getRole().equals("Doktor"))
			u.setRole("DOCTOR");
		if(userRequest.getRole().equals("Medicinska sestra"))
			u.setRole("NURCE");
		
		List<Authority> auth = authService.findByname("ROLE_USER");
		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		u.setAuthorities(auth);
		
		u = this.userRepository.save(u);
		return u;
	}

	@Override
	public User findByJMBG(Long jmbg) {
		return userRepository.getByJMBG(jmbg);
	}

	

	@Override
	public List<Long> getJMBGs(String role) {
		return userRepository.getJMBGs(role);
	}

	@Override
	public List<User> getPacients() {
		return userRepository.getUsersFromRole("PACIENT");
	}
	@Override
	public List<User> getNurces() {
		return userRepository.getUsersFromRole("NURCE");
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getPendingUsers() {
	return userRepository.findPending();
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getDeniedUsers() {
		return userRepository.getDeniedUsers();
	}

	@Override
	public List<Appointment> getDoctorsSchedule(Long id) {
		return  appservice.getDoctorsVisit(id);
	}

	@Override
	public List<User> getDoctors() {
	return userRepository.getUsersFromRole("DOCTOR");
	}

	

}
