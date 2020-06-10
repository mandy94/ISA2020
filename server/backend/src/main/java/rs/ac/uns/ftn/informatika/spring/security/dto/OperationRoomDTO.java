package rs.ac.uns.ftn.informatika.spring.security.dto;

import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;

public class OperationRoomDTO {


    Long id;
    String name;
    String code;
    List<Appointment> scheduler = new ArrayList<Appointment>();
    
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
	public List<Appointment> getScheduler() {
		return scheduler;
	}
	public void setScheduler(List<Appointment> scheduler) {
		this.scheduler = scheduler;
	}

    
}
