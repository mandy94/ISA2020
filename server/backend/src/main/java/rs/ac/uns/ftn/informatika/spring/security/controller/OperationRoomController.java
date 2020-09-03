package rs.ac.uns.ftn.informatika.spring.security.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.spring.security.dto.AppointmentsPerDay;
import rs.ac.uns.ftn.informatika.spring.security.dto.RoomDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.Room;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;
import rs.ac.uns.ftn.informatika.spring.security.model.User;
import rs.ac.uns.ftn.informatika.spring.security.service.AppointmentService;
import rs.ac.uns.ftn.informatika.spring.security.service.OperationRoomService;
import rs.ac.uns.ftn.informatika.spring.security.service.SchedulerTimeService;
import rs.ac.uns.ftn.informatika.spring.security.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationRoomController {
	
	
	@Autowired
	private OperationRoomService orservice;
	
	@Autowired
	private AppointmentService apservice;
	
	@Autowired
	private SchedulerTimeService timeservice;
	
	@Autowired
	private UserService userService;
	
	 private class  DoubleArrays<T>{
		public List<T> available = new ArrayList<T>();
		public List<T> notavailable = new ArrayList<T>();
	}
	
	@GetMapping("/operation-rooms/all")
	public List<Room> getRooms(){	
		return orservice.getOperationRooms();	
	}
	@GetMapping("/operation-room/{id}")
	public List<Room> getSchedulerTimes(@PathVariable Long id){	
		return null;	
	}
	
	@GetMapping("/room-schedule/{doctorid}/{from}/{to}")
	public RoomDTO getRoomScheduleByWeek(@PathVariable Long doctorid, @PathVariable String from, @PathVariable String to){	
	
		String fromDate = covertToDate(from);
		String toDate = covertToDate(to);
		User doctor = userService.findById(doctorid);
		RoomDTO room = new RoomDTO(doctor.getDedicatedRoom().get(0));
		List<Appointment> appointmets = apservice.getDoctorsVisit(doctorid);
		String currentDate = fromDate;
		int maxLoop = 32;
		while(!currentDate.equals(toDate) )
		{
			AppointmentsPerDay day = new AppointmentsPerDay();
			day.setDate(currentDate);
			
			for(SchedulerTime time: doctor.getTimeTable()) {
				day.addTerm(time.getStart(), time.getEnding(), null);
			}	
			room.getTimeTable().add(day);
			currentDate = nextDay(currentDate);
		}
		
		for(Appointment app : appointmets) {
			if(getTimeStampFromStringDate(app.getDate()) >= getTimeStampFromStringDate(fromDate)
					&& getTimeStampFromStringDate(app.getDate()) <= getTimeStampFromStringDate(toDate)) {
				for(AppointmentsPerDay day: room.getTimeTable()) {
					if(day.getDate().equals(app.getDate()))
					{
						User pacient= userService.findById(app.getPacientid());
						day.compareTimeAndAdd(app.getTerm().getStart() , app.getTerm().getEnding(),app.getPacientid(), pacient.getJmbg(), pacient.getFullName());
					}
				}
			}
			maxLoop--;
		}
		return room;
		
			
	}
	@GetMapping("/test/{id}/availability/{date}")
	public List<Appointment> test(@PathVariable Long id, @PathVariable String date){
		
		return  apservice.getAppointmentsForRoomOnDate(id, date);
		}
	
	@GetMapping("/operation-room/{id}/availability/{date}")
	public DoubleArrays<SchedulerTime> getAvailability(@PathVariable Long id, @PathVariable String date){
		List<Appointment> app = apservice.getAppointmentsForRoomOnDate(id, date);
		List<SchedulerTime> available = new ArrayList<SchedulerTime>();
		DoubleArrays<SchedulerTime> returnArrays = new DoubleArrays<SchedulerTime>();

		for(Appointment a: app) {			
				
			if(a.getRoom().getId() == id)
				returnArrays.notavailable.add(a.getTerm());				
		
		}
		
		return returnArrays;

		}
	
	@GetMapping(value="/doctor/{id}/is-available/{date}")
	public DoubleArrays<SchedulerTime> getDoctorsAvailableAcordingToDate(@PathVariable Long id, @PathVariable String date)
	{		
		User u = userService.findById(id);
		DoubleArrays<SchedulerTime> pair = new DoubleArrays<SchedulerTime>();
		pair.notavailable = apservice.getDoctorsBusyHours(id, date);		
		pair.available = apservice.getDoctorsAvaialbleHourse(id, date);
		if(pair.notavailable.size()==0) pair.notavailable = null;
		if(pair.available.size() == 0) pair.available = null;
		return pair;
	
	}
	@GetMapping(value="/available-doctor-list/room/{id}/{date}")
	public List<SchedulerTime> getDoctorsListAvailableAcordingToDate(@PathVariable Long id , @PathVariable String date)
	{
		
		List<User> doctorList = orservice.getRoomById(id).getMandatoryDoctors();
		if(doctorList.size() == 1)
				return apservice.getDoctorsAvaialbleHourse(id, date);

		List<SchedulerTime> candiates = timeservice.findAll();
		List<SchedulerTime> result = new ArrayList<>();
		Boolean isAvaialble = false;
		for(SchedulerTime time: candiates )
		{
			isAvaialble = false;
			Boolean control = true; // for a total true false 
				for(User u: doctorList) {
					if(isInside(time, apservice.getDoctorsAvaialbleHourse(u.getId(), date))) {
						isAvaialble = true;
					}else {
						control = false; // this one is not oke
					}
				}
				if(isAvaialble && control)
					result.add(time);
		}
		if(result.size() == 0)
			return null;
		return result;
	}
	@GetMapping("/operation-room/{id}/mandatory-doctors")
	public List<User> getMandatoryDoctors(@PathVariable Long id){
		return orservice.getRoomById(id).getMandatoryDoctors();
	}
	@DeleteMapping("/delete/doctor/{id}/from-room")
	public List<Room> removeMandatoryDoctor(@PathVariable Long id, @RequestBody Long roomId){
		Room room = orservice.getRoomById(roomId);
		 User u = userService.findById(id);
		 room.getMandatoryDoctors().remove(u);
		 orservice.save(room);
		 return orservice.getOperationRooms();
	}
	
		
	@PutMapping("/add/mandatory-doctor/{id}/room")
	public List<Room> addMandatoryDoctor(@PathVariable Long id, @RequestBody Long roomId){
		Room room= orservice.getRoomById(roomId);
		 User u = userService.findById(id);
		 room.getMandatoryDoctors().add(u);
		 orservice.save(room);
		 return orservice.getOperationRooms();
	}
		
	private boolean isInside(SchedulerTime time, List<SchedulerTime> timeTable) {
		for(SchedulerTime temp: timeTable) {
			if(time.getStart().equals(temp.getStart()) && time.getEnding().equals(temp.getEnding()))
				{
				System.out.println(temp.getStart() + " " + time.getStart());
				return true;}
		}
		return false;
	}
	
	@PostMapping("/operation-room/{id}")
	void makeReservation(@RequestBody Appointment data) {
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
	
//	@PostMapping("/operation-room/request")
//	void requestAppointmentFromAdmin() {
//		executeTaskT(4);
//		
//	}
}
