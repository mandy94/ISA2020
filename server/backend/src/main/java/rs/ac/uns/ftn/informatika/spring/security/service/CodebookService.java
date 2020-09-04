package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Diagnose;
import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.Room;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;

public interface CodebookService {
	 List<Diagnose> getCodesForDiagnoses();
	 List<Medicine> getCodesForMedication();
	 List<Room> getCodesForOperationRooms();
	 List<Therapy> getCodesForTherapies();
	 List<ExaminationReport> getCodesForExaminationReports();
	 
	 List<Diagnose> saveDiagnose(Diagnose d);
	 List<Medicine> saveMedicine(Medicine m);
	 List<Room> saveOperationRoom(Room o);
	 List<Therapy> saveTherapy (Therapy t);
	 
	 Therapy getTherapyById(Long id);
	 Medicine getMedicineById(Long id);
	void deleteDiagnoseById(Long id);
	void deleteRoomById(Long id);
	void deleteTherapyById(Long id);
	void removeMedicineById(Long id);
}
