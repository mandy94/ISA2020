package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;

public interface TimeRepository extends JpaRepository<SchedulerTime, Long> {
	
	@Query("Select t from SchedulerTime t where t.ending = :ending and t.start = :begining ")
	SchedulerTime findByValue(String begining, String ending);
	
	
//	List<SchedulerTime> findTimeByRoomId(Long id);
}
