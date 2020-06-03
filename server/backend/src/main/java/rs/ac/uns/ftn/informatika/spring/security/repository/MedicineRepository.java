package rs.ac.uns.ftn.informatika.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	 
}

