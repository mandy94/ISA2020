package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;

public interface OperationRoomService {

	List<OperationRoom> getRooms();

	List<Appointment> getAppointments(Long id);

	OperationRoom getRoomById(Long room);

}