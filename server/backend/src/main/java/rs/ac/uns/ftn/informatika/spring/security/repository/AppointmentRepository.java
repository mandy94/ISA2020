package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	

	@Query("Select a from Appointment a")
	List<Appointment> getAppointmentsForRoom(Long id);
}
