package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.Prescription;
import rs.ac.uns.ftn.informatika.spring.security.repository.PrescriptionRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {


	@Autowired
	private PrescriptionRepository prescrepo;

	@Override
	public void addPrescription(Medicine med) {
		
		Prescription prescription = new Prescription(med);
		prescription.setNurseId((long) 1);
		prescrepo.save(prescription);
		
	}

	@Override
	public List<Prescription> getPrescptionByNurseId(Long id) {
		return prescrepo.findByNurseId(id);
	}

	@Override
	public void updatePrescriptionById(Long id) {
		prescrepo.updatePrescriptionById(true, id);
	}

	@Override
	public Prescription findById(Long id) {
		return prescrepo.findById(id).orElse(null);
	}

	@Override
	public void save(Prescription p) {
		prescrepo.save(p);
	}

	
	

	
}