package rs.ac.uns.ftn.informatika.spring.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.ac.uns.ftn.informatika.spring.security.dto.AppointmentDTO;

@Entity
@Table(name="APPOINTMENTS")
public class Appointment {
	Long pacientid;
	//List<User> mandatoryDoctors = new ArrayList<User>();
	@Column(name="start")
	String start;
	@Column(name="ending")
	String end;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name="room")
	OperationRoom room;

	public Appointment() {}
	public Appointment(AppointmentDTO appoint) {
		pacientid = appoint.getPacientId();
		start =  appoint.getBegining();
		end = appoint.getEnding();
	}

	public Long getPacientid() {
		return pacientid;
	}

	public void setPacientid(Long pacientid) {
		this.pacientid = pacientid;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	public OperationRoom getRoom() {
		return room;
	}

	public void setRoom(OperationRoom room) {
		this.room = room;
	}

	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}




	
}
