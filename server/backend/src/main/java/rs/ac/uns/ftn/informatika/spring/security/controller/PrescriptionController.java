package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.model.Prescription;
import rs.ac.uns.ftn.informatika.spring.security.service.PrescriptionService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/prescription")
public class PrescriptionController {

	@Autowired
	private PrescriptionService pservice;
	
	@GetMapping(value="/nurse/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Prescription> getPrescrptionByNurseId(@PathVariable Long id){
		return pservice.getPrescptionByNurseId(id);	
	}
	
	@PutMapping(value="/sign/{id}" , consumes = "application/json")
	void updatePrescriptionById(@PathVariable Long id) {
		Prescription drug = pservice.findById(id);
		drug.setSigned(true);
		pservice.save(drug);
	}
	
	@PutMapping(consumes="application/json")
	String testPut() {
		return "radi";	
	}
}