
package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.dto.AppointmentDTO;
import rs.ac.uns.ftn.informatika.spring.security.dto.MessageDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.CalendarAppointment;
import rs.ac.uns.ftn.informatika.spring.security.model.ReservationRoomRequest;
import rs.ac.uns.ftn.informatika.spring.security.model.Room;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.repository.ReservationRoomRequestRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.AppointmentService;
import rs.ac.uns.ftn.informatika.spring.security.service.EmailService;
import rs.ac.uns.ftn.informatika.spring.security.service.OperationRoomService;
import rs.ac.uns.ftn.informatika.spring.security.service.SchedulerTimeService;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {

	@Autowired
	private AppointmentService appService;
	@Autowired
	private OperationRoomService orservice;
	@Autowired
	private SchedulerTimeService timeService;
	@Autowired
	private ReservationRoomRequestRepository reservationRepo;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;
	

	@GetMapping(value = "/visits/{id}")
	public List<CalendarAppointment> getAppointmentByDoctroId(@PathVariable Long id){
		List<CalendarAppointment> calendar = new ArrayList<CalendarAppointment>();
		for(Appointment temp: appService.getDoctorsVisit(id)) {
			if(temp.getRoom()!=null)
			if(temp.getRoom().getId() == id)
			calendar.add(new CalendarAppointment(temp.getDateStart(), temp.getDateEnd(),"Operacija u "+ temp.getRoom().getName()));
		}
		return calendar;
	}
	
	@GetMapping(value = "/room/{id}")
	public List<CalendarAppointment> getAppointmentfForOperationRoom(@PathVariable Long id){
		List<CalendarAppointment> calendar = new ArrayList<CalendarAppointment>();
		for(Appointment temp: appService.getAppointmentsForRoom(id)) {
			if(temp.getRoom()!=null)
			if(temp.getRoom().getId() == id)
			calendar.add(new CalendarAppointment(temp.getDateStart(), temp.getDateEnd(),"Operacija u "+ temp.getRoom().getName()));
		}
		return calendar;
	}
	@PostMapping(value = "/room/new")
	public List<Appointment> createAppointment( @RequestBody AppointmentDTO appoint) {
		Room room = orservice.getRoomById(appoint.getRoom());
		if(room!= null)
		{
			Appointment  app = new Appointment();
			app.setRoom(room);
			app.generateObject(appoint);
			app.setTerm(timeService.findByValue(appoint.getBegining() , appoint.getEnding()));
			
			
			
			System.out.println(app);
			appService.addAppointment(app);
		}
		return appService.getAppointmentsForRoom(appoint.getRoom());
		
	}
	@PostMapping("/room/next-free")
	public void createNextFreeTermRequest(@RequestBody ReservationRoomRequest request) {
		MessageDTO msg = new MessageDTO();
		User me =userService.findById(request.getDoctorId());
		msg.setContent("Molim kolegu da se oredi prvi sledeci slobodan termin operacije. Hvala.");
		msg.setTitle("Zahtev za odredjivanje prvog sledeeg slobognog termina");
		emailService.sendEmail(me.getEmail(), emailService.getMainAdminAdress(), msg);
		reservationRepo.save(request);
		
	
	}
	@GetMapping("/room/next-free")
	public List<ReservationRoomRequest> getNextFreeTerm() {
		return reservationRepo.findAll();
	
	}
}