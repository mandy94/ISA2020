package rs.ac.uns.ftn.informatika.spring.security;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.uns.ftn.informatika.spring.security.model.Room;
import rs.ac.uns.ftn.informatika.spring.security.service.OperationRoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationRoomTests {

	@Autowired
	private OperationRoomService roomService;
	
	@Test
	void createRoom() {
		Room room = new Room();
		room.setId(55l);
		room.setCode("1111");
		room.setName("Proba");
//		room.setType("G");
//		roomService.save(room);
//		assertThat( roomService.getRoomById(55l)).isNotNull();
		
	}

}
