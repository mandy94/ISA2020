package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.service.ReportsService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/examination-reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExaminationReportController {

	@Autowired
	private ReportsService rpservice;
	
	
	@GetMapping("/pacient/{jmbg}")
	public List<ExaminationReport> get(@PathVariable Long jmbg) {
		return  rpservice.getReportsForUser(jmbg);
	}
	@PostMapping(value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void addReport(@RequestBody ExaminationReport report)  throws AccessDeniedException{
		rpservice.addReport(report);
	
	}
	
}
