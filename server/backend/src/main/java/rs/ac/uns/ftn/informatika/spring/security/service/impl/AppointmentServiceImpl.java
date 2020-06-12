package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.repository.AppointmentRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {


	@Autowired
	private AppointmentRepository repo;

	@Override
	public List<Appointment> getAppointmentsForRoom(Long id) {
		return repo.getAppointmentsForRoom(id);
	}

	@Override
	public void addAppointment(Appointment data) {
		repo.save(data);
	}



	
}
