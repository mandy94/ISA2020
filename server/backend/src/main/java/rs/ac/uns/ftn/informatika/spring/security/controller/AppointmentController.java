
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
import rs.ac.uns.ftn.informatika.spring.security.model.CalendarAppointment;
import rs.ac.uns.ftn.informatika.spring.security.service.AppointmentService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {

	@Autowired
	private AppointmentService appService;
	
	@GetMapping(value = "/room/{id}")
	public List<CalendarAppointment> getAppointmentfForOperationRoom(@PathVariable Long id){
		List<CalendarAppointment> calendar = new ArrayList<CalendarAppointment>();
		for(Appointment temp: appService.getAppointmentsForRoom(id)) {
			if(temp.getRoom().getId() == id)
			calendar.add(new CalendarAppointment(temp.getStart(), temp.getEnd(),"Operacija u "+ temp.getRoom().getName()));
		}
		return calendar;
	}
}