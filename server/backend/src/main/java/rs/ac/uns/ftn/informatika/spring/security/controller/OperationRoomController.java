package rs.ac.uns.ftn.informatika.spring.security.controller;

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
import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;
import rs.ac.uns.ftn.informatika.spring.security.repository.AppointmentRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.OperationRoomService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationRoomController {

	@Autowired
	private OperationRoomService orservice;
	
	@Autowired
	private AppointmentRepository apservice;
	
	@GetMapping("/operation-rooms/all")
	public List<OperationRoom> getRooms(){
		return orservice.getRooms();	
	}
	

	
	@GetMapping("/operation-rooms/availability/{id}")
	public List<Appointment> getAvailability(@PathVariable Long id){
		return apservice.getAppointmentsForRoom(id);
		}

}
