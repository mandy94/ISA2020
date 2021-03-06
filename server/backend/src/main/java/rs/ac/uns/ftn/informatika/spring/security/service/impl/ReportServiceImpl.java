package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.repository.ExaminationReportRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.ReportsService;

@Service
public class ReportServiceImpl implements ReportsService {


	@Autowired
	private ExaminationReportRepository repo;

	@Override
	public List<ExaminationReport> getReportsForUser(Long jmbg) {
		return repo.getReportsForUser(jmbg);
	}

	@Override
	@Transactional
	public ExaminationReport addReport(ExaminationReport report) {
		return repo.save(report);
	}

	@Override
	@Transactional
	public void save(ExaminationReport report) {
		repo.save(report);
		
	}

	@Override
	public ExaminationReport findById(Long id) {
		return repo.findById(id).orElse(null);
		
	}

	

	
	
}
