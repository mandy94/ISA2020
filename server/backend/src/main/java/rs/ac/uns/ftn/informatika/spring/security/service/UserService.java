package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.model.UserRequest;

public interface UserService {
    User findById(Long id);
    User findByJMBG(Long jmbg);
    User getByUsername(String username);
    List<Long> getJMBGs(String role);
    User findByUsername(String username);
    List<User> findAll ();
	User save(UserRequest userRequest);
	List<User> getPacients();
	//List<User> getDoctors();
	//List<User> getNurces();
}
