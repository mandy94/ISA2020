package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.model.Diagnose;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.Room;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;
import rs.ac.uns.ftn.informatika.spring.security.service.CodebookService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodebookController {

	@Autowired
	private CodebookService codebookService;
	
	@GetMapping(value = "/codes/diagnoses/all")
	public List<Diagnose> getCodesForDiagnoses() {
		return codebookService.getCodesForDiagnoses();
	}
	@PostMapping(value= "/codes/diagnose",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Diagnose> addDiagnose(@RequestBody Diagnose diag){
		Diagnose ndiag = new Diagnose();
		ndiag.setCode(diag.getCode());
		ndiag.setName(diag.getName());
		
		return codebookService.saveDiagnose(ndiag);
	}
	
	@DeleteMapping(value= "/codes/diagnose",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteDiagnose(@RequestBody Long id){
		codebookService.deleteDiagnoseById(id);
	}
	@DeleteMapping(value = "/codes/med",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteMed(@RequestBody Long id){		
		codebookService.removeMedicineById(id);
	}
	
	@DeleteMapping(value= "/codes/room",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRoom(@RequestBody Long id){
		codebookService.deleteRoomById(id);
	}
	@DeleteMapping(value= "/codes/therapy",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteTherpay(@RequestBody Long id){
		codebookService.deleteTherapyById(id);
	}
	@PostMapping(value = "/codes/therapy",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Therapy> addTherapy(@RequestBody Therapy t){
		Therapy nt= new Therapy();
		nt.setName(t.getName());
		return codebookService.saveTherapy(nt);
		
	}
	@PostMapping(value = "/codes/room",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Room> addRoom(@RequestBody Room o){
		Room or = new Room();
		or.setCode(o.getCode());
		or.setName(o.getName());
	
		return codebookService.saveOperationRoom(or);
		}
	
	@PostMapping(value = "/codes/med",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Medicine> addmed(@RequestBody Medicine m){
		Medicine mm = new Medicine();
		mm.setName(m.getName());
		return codebookService.saveMedicine(mm);
		}
	
	
	
	@GetMapping("/codes/therapies")
	public List<Therapy> getCodesForTherpaies() {
		return codebookService.getCodesForTherapies();
	}
	
	@GetMapping("/codes/rooms")
	public List<Room> getCodesForRooms() {
		return codebookService.getCodesForOperationRooms();
	}
	@GetMapping("/codes/medications/all")
	public List<Medicine> getCodesForMeds() {
		return codebookService.getCodesForMedication();
	}
}
