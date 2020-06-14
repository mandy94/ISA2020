
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
import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.CalendarAppointment;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;
import rs.ac.uns.ftn.informatika.spring.security.service.AppointmentService;
import rs.ac.uns.ftn.informatika.spring.security.service.OperationRoomService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {

	@Autowired
	private AppointmentService appService;
	@Autowired
	private OperationRoomService orservice;
	
	
	@GetMapping(value = "/room/{id}")
	public List<CalendarAppointment> getAppointmentfForOperationRoom(@PathVariable Long id){
		List<CalendarAppointment> calendar = new ArrayList<CalendarAppointment>();
		for(Appointment temp: appService.getAppointmentsForRoom(id)) {
			if(temp.getRoom().getId() == id)
			calendar.add(new CalendarAppointment(temp.getDateStart(), temp.getDateEnd(),"Operacija u "+ temp.getRoom().getName()));
		}
		return calendar;
	}
	@PostMapping(value = "/room/new")
	public List<Appointment> createAppointment( @RequestBody AppointmentDTO appoint) {
		System.out.println(appoint);
		OperationRoom room = orservice.getRoomById(appoint.getRoom());
		if(room!= null)
		{
			Appointment  app = new Appointment(appoint);
			app.setRoom(room);
			
			appService.addAppointment(app);
		}
		return appService.getAppointmentsForRoom(appoint.getRoom());
		
	}
}