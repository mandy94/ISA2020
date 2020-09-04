package rs.ac.uns.ftn.informatika.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.informatika.spring.security.model.ReservationRoomRequest;

public interface ReservationRoomRequestRepository  extends JpaRepository<ReservationRoomRequest, Long> {

}
