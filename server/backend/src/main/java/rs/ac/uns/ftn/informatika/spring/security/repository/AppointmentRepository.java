package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	

	@Query("Select a from Appointment a where a.doctorid = :id and a.date = :date")
	List<Appointment> getBusyDoctorsAcordingToDate(@Param("id")Long id, String date);
	
	@Query("Select a from Appointment a where a.room.id = :id and a.date = :date")
	List<Appointment> getAppointmentsForRoomOnDate(Long id, String date);

	@Query("Select v from Appointment v where v.doctorid = :id")
	List<Appointment> getDoctorsVisit(Long id);
}
