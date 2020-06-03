package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.User;

public interface ReportsService {

	List<ExaminationReport> getReportsForUser(Long jmbg);

	void addReport(ExaminationReport report);

	
    
}

