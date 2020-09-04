package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;

public interface ReportsService {

	List<ExaminationReport> getReportsForUser(Long jmbg);

	ExaminationReport addReport(ExaminationReport report);
	void save(ExaminationReport report);

	ExaminationReport findById(Long id);
    
}

