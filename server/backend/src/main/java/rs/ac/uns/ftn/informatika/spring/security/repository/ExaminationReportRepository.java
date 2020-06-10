package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
@Repository
public interface ExaminationReportRepository extends JpaRepository<ExaminationReport, Long> {
	
	@Query("Select e from ExaminationReport e where e.pacient.jmbg = :jmbg")
	List<ExaminationReport> getReportsForUser(Long jmbg);

	@Transactional
	@Modifying
	@Query(value ="INSERT INTO ExaminationReport (details) VALUES (:details)", nativeQuery = true)
	void saveDetails(@Param("details")String obj);

	
}