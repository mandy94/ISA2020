package rs.ac.uns.ftn.informatika.spring.security.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.spring.security.dto.DoctorTimeTableDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.repository.AppointmentRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.AppointmentService;
import rs.ac.uns.ftn.informatika.spring.security.service.SchedulerTimeService;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

@Service
public class AppointmentServiceImpl implements AppointmentService {


	@Autowired
	private AppointmentRepository repo;
	@Autowired
	private UserService userService;
	
	private TaskScheduler scheduler;

	@Override
	public List<Appointment> getAppointmentsForRoom(Long id) {
		return null;
	}

	@Override
	@Transactional
	public void addAppointment(Appointment data) {
		repo.save(data);
	}

	@Override
	public List<Appointment> getDoctorsVisit(Long id) {
		return repo.getDoctorsVisit(id);
	}

	

	@Override
	public List<Appointment> getBusyDoctorsAcordingToDate(Long id, String date) {
		List<Appointment> apps = repo.getBusyDoctorsAcordingToDate(id, covertToDate(date));				
		return apps;
	}
	@Override
	public List<Appointment> getAvailableDoctorsAcordingToDate(Long id, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsForRoomOnDate(Long id,String date) {
		return repo.getAppointmentsForRoomOnDate(id, covertToDate(date));
	}

	@Override
	public List<SchedulerTime> getDoctorsBusyHours(Long id, String date) {
		List<Appointment> app = getBusyDoctorsAcordingToDate(id,date);
		List<SchedulerTime> times = new ArrayList<>();
		for(Appointment ap : app) {
			times.add(ap.getTerm());
		}
		return times;
	}

	@Override
	public List<SchedulerTime> getDoctorsAvaialbleHourse(Long id, String date) {
		List<Appointment> app = getBusyDoctorsAcordingToDate(id,date);
		User u = userService.findById(id);				
		List<SchedulerTime> times =  u.getTimeTable();
		List<SchedulerTime> copyTimes = new ArrayList<>(times);
		for(Appointment ap : app) {
			for(SchedulerTime time:times) {
				if(time.getStart().equals(ap.getTerm().getStart()))
				{
					copyTimes.remove(time);
				}
			}
		}
		return copyTimes;
	}

	

	private String covertToDate(String date) {
		
		return date.substring(0, 2) + "."+date.substring(2,4)+"."+date.substring(4,8);
	}

	Long getTimeStampFromStringDate(String date){
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date parsed;
		try {
			parsed = df.parse(date);
			return parsed.getTime()/1000*1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	String getTimeFromTimeStampToString(Long stamp) {
		return new SimpleDateFormat("dd.MM.yyyy").format(new Date(stamp));
	}
	
	String nextDay(String date) {
		Long ts = getTimeStampFromStringDate(date); // IN MILISECONDS
		return getTimeFromTimeStampToString(ts+24*60*60*1000);
		
	}

	Runnable exampleRunnable = new Runnable(){
	    @Override
	    public void run() {
	        System.out.println("Works");
	    }
	};
	
	@Async
	public void executeTaskT(int seconds) {
	    ScheduledExecutorService localExecutor = Executors.newSingleThreadScheduledExecutor();
	    scheduler = new ConcurrentTaskScheduler(localExecutor);
	
	    scheduler.schedule(exampleRunnable,
	            new Date(System.currentTimeMillis()+1000L*seconds));
	}

	



	
}
