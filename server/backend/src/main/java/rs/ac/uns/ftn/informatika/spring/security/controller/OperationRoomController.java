package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jmx.snmp.Timestamp;

import rs.ac.uns.ftn.informatika.spring.security.dto.AppointmentsPerDay;
import rs.ac.uns.ftn.informatika.spring.security.dto.RoomDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.Room;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.repository.AppointmentRepository;
import rs.ac.uns.ftn.informatika.spring.security.service.OperationRoomService;
import rs.ac.uns.ftn.informatika.spring.security.service.SchedulerTimeService;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationRoomController {
	private TaskScheduler scheduler;
	
	@Autowired
	private OperationRoomService orservice;
	
	@Autowired
	private AppointmentRepository apservice;
	
	@Autowired
	private SchedulerTimeService timeservice;
	@Autowired
	private UserService userService;
	
	private class DoubleArrays{
		public List<SchedulerTime> available = new ArrayList<SchedulerTime>();
		public List<SchedulerTime> notavailable = new ArrayList<SchedulerTime>();
	}
	
	@GetMapping("/operation-rooms/all")
	public List<Room> getRooms(){	
		return orservice.getOperationRooms();	
	}
	@GetMapping("/operation-room/{id}")
	public List<Room> getSchedulerTimes(@PathVariable Long id){	
		return null;	
	}
	
	@GetMapping("/room-schedule/day/{doctorid}")
	public RoomDTO getRoomScheduleByDay(@PathVariable Long doctorid){	
		User doctor = userService.findById(doctorid);
		RoomDTO room = new RoomDTO(doctor.getDedicatedRoom().get(0));
		List<Appointment> appointmets = apservice.getDoctorsVisit(doctorid);
		// Da se dobije radno vreme doktora
		AppointmentsPerDay day = new AppointmentsPerDay();
		for(SchedulerTime time: timeservice.getTimesForOperationRoom(room.getId())) {
			day.addTerm(time.getStart(), time.getEnding(), null);
		}
		room.getTimeTable().add(day);
		room.getTimeTable().get(0).setDate(getTodayDate());
		for(Appointment app :appointmets) {
			if(app.getDate().equals(getTodayDate())) {
				room.getTimeTable().get(0).compareTimeAndAdd(app.getStart(),
						app.getEnd(), app.getPacientid());
			}
		}
		return room;
			
	}
	
	Long getTimeStampFromStringDate(String date){
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date parsed;
		try {
			parsed = df.parse(date);
			Timestamp ti = new Timestamp(parsed.getTime());
			return ti.getDateTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	String getTimeFormTimeStampToString(Long stamp) {
		return new SimpleDateFormat("dd.MM.yyyy").format(new Date(stamp));
	}
	@GetMapping("/room-schedule/week/{doctorid}")
	public RoomDTO getRoomScheduleByWeek(@PathVariable Long doctorid){	
		User doctor = userService.findById(doctorid);
		RoomDTO room = new RoomDTO(doctor.getDedicatedRoom().get(0));
		List<Appointment> appointmets = apservice.getDoctorsVisit(doctorid);

		Long monday = getLastMondayTimeStamp();
		Long sunday = monday + 7*24*60*60*1000L;
		for(int j = 0 ; j < 7 ; j++)
		{
			AppointmentsPerDay day = new AppointmentsPerDay();
			day.setDate(getTimeFormTimeStampToString(monday*1000 + 24*60*1000*60*(j+1)));
			System.out.println(monday + 24*60*60*(j+1));
			
			for(SchedulerTime time: timeservice.getTimesForOperationRoom(room.getId())) {
				day.addTerm(time.getStart(), time.getEnding(), null);
			}	
			room.getTimeTable().add(day);
		}
		int i = 0;
		for(Appointment app: appointmets) {
			if(getTimeStampFromStringDate(app.getDate())>=monday && getTimeStampFromStringDate(app.getDate())<=sunday) {
				
//				for(Appointment ap :appointmets) {
//					if(ap.getDate().equals(getTodayDate())) {
//						room.getTimeTable().get(i).compareTimeAndAdd(ap.getStart(),
//								ap.getEnd(), ap.getPacientid());
//					}
//				}
				i++;// next day
			
			} // if
		}
		return room;
		
			
	}
	@GetMapping("/operation-room/{id}/availability/{date}")
	public DoubleArrays getAvailability(@PathVariable Long id, @PathVariable String date){
		String parsed = covertToDate(date);	
		List<Appointment> app = apservice.getAppointmentsForRoomOnDate(id, parsed);
		List<SchedulerTime> terms = timeservice.getTimesForOperationRoom(id);
//		List<SchedulerTime> available = new ArrayList<SchedulerTime>(terms);
		DoubleArrays returnArrays = new DoubleArrays();
		if(app.size() == terms.size()) return null; // sve je popunjeno

		if(app.size() == 0) // znaci da je sve slobodno
		{
			returnArrays.available = terms;
		}
		for(Appointment a: app) {
			for(SchedulerTime term : terms) {
				
				if(term.getStart().equals(a.getStart()))
				{
					returnArrays.notavailable.add(term);				
				}else {
					returnArrays.available.add(term);
				}
			}
		}
		
		return returnArrays;
		}
	
	@GetMapping("/operation-room/{id}/mandatory-doctors")
	public List<User> getMandatoryDoctors(@PathVariable Long id){
		return orservice.getRoomById(id).getMandatoryDoctors();
	}
	
	@PostMapping("/operation-room/{id}")
	void makeReservation(@RequestBody Appointment data) {
	}
	
	@PostMapping("/operation-room/request")
	void requestAppointmentFromAdmin() {
		executeTaskT(4);
		
	}
	private String covertToDate(String date) {
		
		return date.substring(0, 2) + "."+date.substring(2,4)+"."+date.substring(4,8);
	}
	private String getTodayDate() {
		 SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		 Date date = new Date();
		 return formatter.format(date);
	}
	private Long getLastMondayTimeStamp() {
		LocalDate date = LocalDate.now(); 
		 DayOfWeek day = date.getDayOfWeek();
		 SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");		 
		 return System.currentTimeMillis()/1000-24*60*60*(day.getValue()-1);
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
