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
	
	@GetMapping("/codes/diagnoses/all")
	public List<Diagnose> getCodesForDiagnoses() {
		return codebookService.getCodesForDiagnoses();
	}
	@GetMapping("/codes/therapies")
	public List<Therapy> getCodesForTherpaies() {
		return codebookService.getCodesForTherapies();
	}
	
	@GetMapping("/codes/rooms")
	public List<OperationRoom> getCodesForRooms() {
		return codebookService.getCodesForOperationRooms();
	}
	@GetMapping("/codes/medications/all")
	public List<Medicine> getCodesForMeds() {
		return codebookService.getCodesForMedication();
	}
}
