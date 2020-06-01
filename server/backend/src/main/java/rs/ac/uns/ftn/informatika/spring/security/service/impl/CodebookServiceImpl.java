
package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.Diagnose;
import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;
import rs.ac.uns.ftn.informatika.spring.security.repository.CodebookRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.CodebookService;

@Service
public class CodebookServiceImpl implements CodebookService {


	@Autowired
	private CodebookRepository repo;

	@Override
	public List<Diagnose> getCodesForDiagnoses() {
		return repo.getDiagnoses();
	}

	@Override
	public List<Medicine> getCodesForMedication() {
		return repo.getMeds();
	}

	@Override
	public List<ExaminationReport> getCodesForExaminationReports() {
		return repo.getReports();
	}

	@Override
	public List<OperationRoom> getCodesForOperationRooms() {
		return repo.getRooms();
	}
	@Override
	public List<Therapy> getCodesForTherapies() {
		return repo.getTherapies();
	}
}
