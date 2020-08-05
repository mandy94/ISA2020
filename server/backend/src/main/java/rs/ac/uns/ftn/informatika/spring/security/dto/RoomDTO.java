package rs.ac.uns.ftn.informatika.spring.security.dto;

import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Room;

public class RoomDTO{
	Long id;
	String name;
	String code;
	String type;
//	List<SchedulerTime> workingHours = new ArrayList<>();
	List<AppointmentsPerDay> timeTable = new ArrayList<AppointmentsPerDay>();
	public RoomDTO() {}
	public RoomDTO(Room room) {
		this.id = room.getId();
		this.name = room.getName();
		this.code = room.getCode();
		this.type = room.getType();
//		this.timeTable = new WeeklyAppointments(room);
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<AppointmentsPerDay> getTimeTable() {
		return timeTable;
	}
	public void setTimeTable(List<AppointmentsPerDay> timeTable) {
		this.timeTable = timeTable;
	}
//	public List<SchedulerTime> getWorkingHours() {
//		return workingHours;
//	}
//	public void setWorkingHours(List<SchedulerTime> workingHours) {
//		this.workingHours = workingHours;
//	}
	
	
}