package rs.ac.uns.ftn.informatika.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.spring.security.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
    
    @Query(" SELECT u FROM User u WHERE u.jmbg = :jmbg")
    User getByJMBG( Long jmbg);
    
    @Query("SELECT u.jmbg FROM User u WHERE u.role = :role ")
    List<Long> getJMBGs(String role);
    
    @Query("SELECT u FROM User u WHERE u.role = :role ")
    List<User> getUsersFromRole(String role);
}


