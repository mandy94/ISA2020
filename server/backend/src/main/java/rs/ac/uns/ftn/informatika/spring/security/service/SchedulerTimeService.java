package rs.ac.uns.ftn.informatika.spring.security.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;

public interface SchedulerTimeService {

	List<SchedulerTime> getTimesForOperationRoom(Long id);
	void addTime(SchedulerTime data);
    
}

