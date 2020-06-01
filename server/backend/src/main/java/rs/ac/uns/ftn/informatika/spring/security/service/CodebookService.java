package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Diagnose;
import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;

public interface CodebookService {
	 List<Diagnose> getCodesForDiagnoses();
	 List<Medicine> getCodesForMedication();
	 List<ExaminationReport> getCodesForExaminationReports();
	 List<OperationRoom> getCodesForOperationRooms();
	 List<Therapy> getCodesForTherapies();
}
