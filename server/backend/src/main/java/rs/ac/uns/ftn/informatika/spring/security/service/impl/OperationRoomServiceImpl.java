package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.Room;
import rs.ac.uns.ftn.informatika.spring.security.repository.AppointmentRepository;
import rs.ac.uns.ftn.informatika.spring.security.repository.OperationRoomRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.OperationRoomService;

@Service
public class OperationRoomServiceImpl implements OperationRoomService {

	@Autowired
	private OperationRoomRepository repo;
	@Autowired
	private AppointmentRepository aprepo;

	@Override
	public List<Room> getRooms() {
		return repo.findAll();
	}

	@Override
	public List<Appointment> getAppointments(Long id) {
	//	return aprepo.getAppointmentsForRoom(id);
		return null;
	}

	@Override
	public Room getRoomById(Long id) {
		return repo.findById(id).orElse(null);
		
	}

	@Override
	public List<Room> getOperationRooms() {
		return repo.findOperationRooms();
	}

	@Override
	public List<Room> getVisitRooms() {
		return repo.findVisitRoom();
	}

	@Override
	@Transactional
	public void save(Room room) {
		repo.save(room);
	}

	


}