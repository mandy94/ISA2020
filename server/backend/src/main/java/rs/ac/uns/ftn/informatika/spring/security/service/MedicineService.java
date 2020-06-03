package rs.ac.uns.ftn.informatika.spring.security.service;

import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;

public interface MedicineService {

	Medicine findById(Long id);
	void saveMedicine(Medicine med);

	
    
}

