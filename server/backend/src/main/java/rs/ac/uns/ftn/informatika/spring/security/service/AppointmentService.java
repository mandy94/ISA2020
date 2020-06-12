package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.CalendarAppointment;

public interface AppointmentService {

	List<Appointment> getAppointmentsForRoom(Long id);
	void addAppointment(Appointment data);
    
}

