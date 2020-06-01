package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.model.Diagnose;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;
import rs.ac.uns.ftn.informatika.spring.security.service.CodebookService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodebookController {

	@Autowired
	private CodebookService codebookService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/codes/diagnoses")
	public List<Diagnose> getCodesForDiagnoses() {
		return codebookService.getCodesForDiagnoses();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/codes/therapies")
	public List<Therapy> getCodesForTherpaies() {
		return codebookService.getCodesForTherapies();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/codes/rooms")
	public List<OperationRoom> getCodesForRooms() {
		return codebookService.getCodesForOperationRooms();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/codes/medication")
	public List<Medicine> getCodesForMeds() {
		return codebookService.getCodesForMedication();
	}
}
