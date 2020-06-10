package rs.ac.uns.ftn.informatika.spring.security.dto;

import rs.ac.uns.ftn.informatika.spring.security.model.OperationRoom;

public class AppointmentDTO {

	Long id;
	Long room;
	Long pacientId;
	String begining, ending;
	// docss
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoom() {
		return room;
	}
	public void setRoom(Long room) {
		this.room = room;
	}
	public Long getPacientId() {
		return pacientId;
	}
	public void setPacientId(Long pacientId) {
		this.pacientId = pacientId;
	}
	public String getBegining() {
		return begining;
	}
	public void setBegining(String begining) {
		this.begining = begining;
	}
	public String getEnding() {
		return ending;
	}
	public void setEnding(String ending) {
		this.ending = ending;
	}
	@Override
	public String toString() {
		return "AppointmentDTO [id=" + id + ", room=" + room + ", pacientId=" + pacientId + ", begining=" + begining
				+ ", ending=" + ending + "]";
	}
}
