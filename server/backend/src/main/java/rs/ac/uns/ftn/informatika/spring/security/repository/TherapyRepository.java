
package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;

public interface TherapyRepository extends JpaRepository<Therapy, Long> {


	@Query("Select t FROM Therapy t")
	List<Therapy> getTherapies();

	

	
}

