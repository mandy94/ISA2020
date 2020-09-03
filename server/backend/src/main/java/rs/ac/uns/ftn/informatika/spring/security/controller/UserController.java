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

import rs.ac.uns.ftn.informatika.spring.security.dto.DoctorTimeTableDTO;
import rs.ac.uns.ftn.informatika.spring.security.dto.UserDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.service.AppointmentService;
import rs.ac.uns.ftn.informatika.spring.security.service.PacientDataService;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PacientDataService pacientDataService;

	@Autowired
	private AppointmentService appointmentService;
	
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
	@GetMapping("/user/nurces")
	@PreAuthorize("hasRole('DOCTOR')")
	public List<User> getNurces(){
		return this.userService.getNurces();
	}
	@GetMapping("/user/pacients")
	@PreAuthorize("hasRole('DOCTOR')")
	public List<User> getPatients() {
		return this.userService.getPacients();
	}
	@GetMapping("/user/pacient/{jmbg}")
	@PreAuthorize("hasRole('DOCTOR')")
	public UserDTO loadByJMBG(@PathVariable Long jmbg) {
		User ret =  this.userService.findByJMBG(jmbg);
		System.out.println(ret.getData());
		return new UserDTO(ret);
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

	@GetMapping("/user/doctor")
	public List<User> getDoctors() {
		return this.userService.getDoctors();
	}

	@GetMapping("/whoami")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}
	
	@GetMapping(value="/doctor/{id}/timetable")
	public List<SchedulerTime> getDoctorsTimetable(@PathVariable Long id)
	{
		DoctorTimeTableDTO data = new DoctorTimeTableDTO();
		User u = userService.findById(id);				
		return u.getTimeTable();
		
	}

	
	@GetMapping(value="/doctor/scheduler/{id}")
	public List<Appointment> getDoctorsShcedulerTime(@PathVariable Long id)
	{
		List<Appointment> tt = userService.getDoctorsSchedule(id);
		for(Appointment t : tt)
		{
			t.getTerm().setEnding(t.getDateEnd());
			t.getTerm().setStart(t.getDateStart());
		}
		return tt;
	}
	
}
