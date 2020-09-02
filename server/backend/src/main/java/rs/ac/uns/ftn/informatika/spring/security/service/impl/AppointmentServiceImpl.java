package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;

import javax.transaction.Transactional;

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
//		return repo.getAppointmentsForRoom(id);
		return null;
	}

	@Override
	@Transactional
	public void addAppointment(Appointment data) {
		repo.save(data);
	}

	@Override
	public List<Appointment> getDoctorsVisit(Long id) {
		return repo.getDoctorsVisit(id);
	}



	
}
