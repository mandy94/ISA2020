package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.informatika.spring.security.model.Room;

public interface OperationRoomRepository extends JpaRepository<Room, Long> {
	
	List<Room> findAll();
	@Query("Select w from Room w where w.type = 'OPERATION'")
	List<Room> findOperationRooms();
	@Query("Select w from Room w where w.type = 'VISIT'")
	List<Room> findVisitRoom();

	
//	@Query("Select m from MandatoryDoctors m where m.user_id=:id")
//	Long getRoomIdFromMandatoryDoctor(@Param("id") Long id);
}
