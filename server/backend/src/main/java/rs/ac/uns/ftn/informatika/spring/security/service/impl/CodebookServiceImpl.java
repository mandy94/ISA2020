
package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.Diagnose;
import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.Room;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;
import rs.ac.uns.ftn.informatika.spring.security.repository.DiagnoseRepository;
import rs.ac.uns.ftn.informatika.spring.security.repository.MedicineRepository;
import rs.ac.uns.ftn.informatika.spring.security.repository.OperationRoomRepository;
import rs.ac.uns.ftn.informatika.spring.security.repository.TherapyRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.CodebookService;

@Service
public class CodebookServiceImpl implements CodebookService {


	@Autowired
	private DiagnoseRepository drepo;

	@Autowired
	private TherapyRepository trepo;


	@Autowired
	private MedicineRepository mrepo;


	@Autowired
	private OperationRoomRepository orepo;


	// DIAGNOSES
	@Override
	public List<Diagnose> getCodesForDiagnoses() {
		return drepo.findAll();
	}


	
	@Override
	@Transactional
	public List<Diagnose> saveDiagnose(Diagnose d) {
		drepo.save(d);
		return drepo.findAll();
	}
	
	// ROOOOMS
	@Override
	public List<Room> getCodesForOperationRooms() {
		return orepo.findAll();
	}
	

	@Override
	@Transactional
	public List<Room> saveOperationRoom(Room o) {
		orepo.save(o);
		return orepo.findAll();
	}

	// 
	@Override
	public List<Therapy> getCodesForTherapies() {
		return trepo.findAll();
	}
	@Override
	@Transactional
	public List<Therapy> saveTherapy(Therapy t) {
		trepo.save(t);
		return trepo.findAll();
	}
	@Override
	public Therapy getTherapyById(Long id) {
		return trepo.findById(id).orElse(null);
	}


	// MEDICATIONSS
	@Override
	@Transactional
	public List<Medicine> saveMedicine(Medicine m) {
		 mrepo.save(m);
		 return mrepo.findAll();
	}
	
	@Override
	public List<Medicine> getCodesForMedication() {
		return mrepo.findAll();
	}


	@Override
	public Medicine getMedicineById(Long id) {
		return mrepo.findById(id).orElse(null);
	}

	// othersss
	@Override
	public List<ExaminationReport> getCodesForExaminationReports() {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public void deleteDiagnoseById(Long id) {
		 drepo.deleteById(id);
	}



	@Override
	public void deleteRoomById(Long id) {
		 orepo.deleteById(id);
		
	}



	@Override
	public void deleteTherapyById(Long id) {
		 trepo.deleteById(id);		
	}



	@Override
	public void removeMedicineById(Long id) {
		mrepo.deleteById(id);
		
	}



	
	
}
