package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;

public interface OperationRoomRepository extends JpaRepository<OperationRoom, Long> {
	
	List<OperationRoom> findAll();

}
