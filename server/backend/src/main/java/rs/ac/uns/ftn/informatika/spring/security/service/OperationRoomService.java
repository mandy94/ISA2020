package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.Room;

public interface OperationRoomService {

	List<Room> getRooms();
	List<Room> getOperationRooms();
	List<Room> getVisitRooms();
	List<Appointment> getAppointments(Long id);
	Room getRoomById(Long room);
	void save(Room room);

}