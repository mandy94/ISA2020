package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.dto.ExaminationReportDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.security.TokenUtils;
import rs.ac.uns.ftn.informatika.spring.security.service.MedicineService;
import rs.ac.uns.ftn.informatika.spring.security.service.PrescriptionService;
import rs.ac.uns.ftn.informatika.spring.security.service.ReportsService;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/examination-report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExaminationReportController {

	@Autowired
	private ReportsService rpservice;
	@Autowired
	private UserService userservice;
	
	@Autowired
	private MedicineService mdservice;
	@Autowired
	private PrescriptionService presservice;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@GetMapping("/pacient/{jmbg}")
	public List<ExaminationReport> get(@PathVariable Long jmbg) {
		
				
		return  rpservice.getReportsForUser(jmbg);
	}
	
	@PostMapping(value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void addReport(@RequestBody ExaminationReportDTO reportdto)  throws AccessDeniedException{
		
		System.out.println(reportdto);
		
			ExaminationReport report = new ExaminationReport();
			User pacient = userservice.findById(reportdto.getPacientid());
			User doctor = userservice.findById(reportdto.getDoctorid());
			
			report.copyFromDTO(reportdto);
			for(Long med_id: reportdto.getMedication())
			{
				Medicine med = mdservice.findById(med_id);
				if(med != null) {				
					report.getMedication().add(med);
					presservice.addPrescription(med);
				}
			}
			if(pacient!= null)report.setPacient(pacient);
			if(doctor!= null)report.setDoctor(doctor);
			report = rpservice.addReport(report);
	}
	
}
