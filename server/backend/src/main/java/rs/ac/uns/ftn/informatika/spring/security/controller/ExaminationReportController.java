package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.dto.ExaminationReportDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.security.TokenUtils;
import rs.ac.uns.ftn.informatika.spring.security.service.CodebookService;
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
	private CodebookService cdservice;
	@Autowired
	private PrescriptionService presservice;
	
	
	@GetMapping("/pacient/{jmbg}")
	public List<ExaminationReportDTO> get(@PathVariable Long jmbg) {
		List<ExaminationReport> rep =rpservice.getReportsForUser(jmbg);
		List<ExaminationReportDTO> res = new ArrayList<ExaminationReportDTO>();
		for(ExaminationReport report : rep) {
			ExaminationReportDTO temp = new ExaminationReportDTO(report);
			res.add(temp);
		}
		return res;
	}
	
	@PostMapping(value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void addReport(@RequestBody ExaminationReportDTO reportdto)  throws AccessDeniedException{
		
		
			ExaminationReport report = new ExaminationReport();
			User pacient = userservice.findById(reportdto.getPacientid());
			User doctor = userservice.findById(reportdto.getDoctorid());

			report.copyFromDTO(reportdto);
			for(Medicine med_id: reportdto.getMedication())
			{
				Medicine med = cdservice.getMedicineById(med_id.getId());
				if(med != null) {				
					report.getMedication().add(med);
					presservice.addPrescription(med);
				}
			}
			for(Therapy t_id : reportdto.getTherapies()) {
				Therapy t = cdservice.getTherapyById(t_id.getId());
				if(t!= null) {
					report.getTherapies().add(t);
				}
			}
			if(pacient!= null)report.setPacient(pacient);
			if(doctor!= null)report.setDoctor(doctor);
			report = rpservice.addReport(report);
	}
	
}
