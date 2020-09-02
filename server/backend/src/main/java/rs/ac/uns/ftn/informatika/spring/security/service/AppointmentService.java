package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;

public interface AppointmentService {

	List<Appointment> getAppointmentsForRoom(Long id);
	List<Appointment> getAvailableDoctorsAcordingToDate(Long id, String date);
	List<Appointment> getBusyDoctorsAcordingToDate(Long id, String date);	
	List<Appointment> getAppointmentsForRoomOnDate(Long id, String date);
	List<Appointment> getDoctorsVisit(Long id);
	void addAppointment(Appointment data);
	List<SchedulerTime> getDoctorsBusyHours(Long id, String date);
	List<SchedulerTime> getDoctorsAvaialbleHourse(Long id, String date);
    
}

