package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	@Query("Select t FROM Medicine t")
	List<Medicine> getMedicine();

}

