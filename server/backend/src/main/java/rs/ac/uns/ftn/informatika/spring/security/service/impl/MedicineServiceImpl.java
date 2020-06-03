package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.repository.MedicineRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {


	@Autowired
	private MedicineRepository repo;

	@Override
	public void saveMedicine(Medicine med) {
		repo.save(med);
		}

	@Override
	public  Medicine findById(Long id) {
		return repo.findById(id).orElse(null);
	}



	
}
