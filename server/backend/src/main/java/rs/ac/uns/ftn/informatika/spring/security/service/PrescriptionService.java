package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.Prescription;

public interface PrescriptionService {

	void addPrescription(Medicine med);
	
    List<Prescription> getPrescptionByNurseId(Long id);

	void updatePrescriptionById(Long id);

	Prescription findById(Long id);
	
	void save(Prescription p);
}

