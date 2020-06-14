package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.repository.AppointmentRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.OperationRoomService;
import rs.ac.uns.ftn.informatika.spring.security.service.SchedulerTimeService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationRoomController {

	@Autowired
	private OperationRoomService orservice;
	
	@Autowired
	private AppointmentRepository apservice;
	
	@Autowired
	private SchedulerTimeService timeservice;
	@GetMapping("/operation-rooms/all")
	public List<OperationRoom> getRooms(){
		return orservice.getRooms();	
	}
	

	@GetMapping("/operation-room/{id}/availability/{date}")
	public List<SchedulerTime> getAvailability(@PathVariable Long id, @PathVariable String date){
		List<Appointment> app = apservice.getAppointmentsForRoomOnDate(id, date);
		List<SchedulerTime> terms =timeservice.getTimesForOperationRoom(id);
		List<SchedulerTime> available = new ArrayList<SchedulerTime>(terms);
		
		for(Appointment a: app) {
			for(SchedulerTime term : terms) {
				
				if(term.getStart().equals(a.getStart()))
				{
					available.remove(term);				
				}
			}
		}
		return available;
		}
	
	@GetMapping("/operation-room/{id}/mandatory-doctors")
	public List<User> getMandatoryDoctors(@PathVariable Long id){
		return orservice.getRoomById(id).getMandatoryDoctors();
		
	
	}

}
