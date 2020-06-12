package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;
import rs.ac.uns.ftn.informatika.spring.security.repository.AppointmentRepository;
import rs.ac.uns.ftn.informatika.spring.security.repository.TimeRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.AppointmentService;
import rs.ac.uns.ftn.informatika.spring.security.service.SchedulerTimeService;

@Service
public class SchedulerTimeServiceImpl implements SchedulerTimeService {


	@Autowired
	private TimeRepository repo;

	@Override
	public List<SchedulerTime> getTimesForOperationRoom(Long id) {
		return repo.findTimeByRoomId(id);
	}

	@Override
	public void addTime(SchedulerTime data) {
		repo.save(data);
		}


	
}
