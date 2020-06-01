package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.informatika.spring.security.model.Diagnose;
import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;
import rs.ac.uns.ftn.informatika.spring.security.model.User;

public interface CodebookRepository extends JpaRepository<User, Long> {

	@Query("Select d FROM Diagnose d")
	List<Diagnose> getDiagnoses();
	
	@Query("Select m FROM Medicine m")
	List<Medicine> getMeds();

	@Query("Select r FROM ExaminationReport r")
	List<ExaminationReport> getReports();

	@Query("Select t FROM Therapy t")
	List<Therapy> getTherapies();

	@Query("Select o FROM OperationRoom o")
	List<OperationRoom> getRooms();
    
}

