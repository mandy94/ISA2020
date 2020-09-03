package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.spring.security.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
    
    @Query("Select u from User u WHERE u.status = 'ACTIVE'")
   List<User> findAll();
    @Query(" SELECT u FROM User u WHERE u.jmbg = :jmbg")
    User getByJMBG( Long jmbg);
    
    @Query("SELECT u.jmbg FROM User u WHERE u.role = :role ")
    List<Long> getJMBGs(String role);
    
    @Query("SELECT u FROM User u WHERE u.role = :role ")
    List<User> getUsersFromRole(String role);

    @Query("select u from User u Where u.status = 'PENDING'")
	List<User> findPending();
    
    @Query("select u from User u where u.status = 'DENIED'")
    List<User>getDeniedUsers();

    @Query("Select u from User u ")
    List<User> getDoctorsTimeTable(@Param(value="id") Long id);

	
}


