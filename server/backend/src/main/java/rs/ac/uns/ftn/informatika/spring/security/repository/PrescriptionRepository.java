package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.informatika.spring.security.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

	@Query("Select a from Prescription a where a.nurseId = :id")
	List<Prescription> findByNurseId(Long id);

	@Modifying	
	@Query("Update Prescription p SET p.signed = :status Where p.id = :id")
	void updatePrescriptionById(@Param("status") Boolean status, @Param("id") Long id);
	
	}
