package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;

public interface AppointmentService {

	List<Appointment> getAppointmentsForRoom(Long id);
	void addAppointment(Appointment data);
	List<Appointment> getDoctorsVisit(Long id);
    
}

