
package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;
import rs.ac.uns.ftn.informatika.spring.security.service.SchedulerTimeService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/time", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationRoomTimeController {

	@Autowired
	private SchedulerTimeService timeService;
	
	@GetMapping(value = "/room/{id}")
	public List<SchedulerTime> getTimesForOperationRoom(@PathVariable Long id){
	return timeService.getTimesForOperationRoom(id);
	}

}