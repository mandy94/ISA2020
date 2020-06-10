
package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.Diagnose;
import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;
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


	
	@Override
	public List<Diagnose> getCodesForDiagnoses() {
		return drepo.findAll();
	}

	@Override
	public List<Medicine> getCodesForMedication() {
		return mrepo.findAll();
	}

	

	@Override
	public List<OperationRoom> getCodesForOperationRooms() {
		return orepo.findAll();
	}
	@Override
	public List<Therapy> getCodesForTherapies() {
		return trepo.findAll();
	}

	@Override
	public List<Diagnose> saveDiagnose(Diagnose d) {
		drepo.save(d);
		return drepo.findAll();
	}

	@Override
	public List<Medicine> saveMedicine(Medicine m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperationRoom> saveOperationRoom(OperationRoom o) {
		orepo.save(o);
		return orepo.findAll();
	}

	@Override
	public List<Therapy> saveTherapy(Therapy t) {
		trepo.save(t);
		return trepo.findAll();
	}

	@Override
	public List<ExaminationReport> getCodesForExaminationReports() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Therapy getTherapyById(Long id) {
		return trepo.findById(id).orElse(null);
	}

	@Override
	public Medicine getMedicineById(Long id) {
		return mrepo.findById(id).orElse(null);
	}
}
