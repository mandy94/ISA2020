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
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	Long pacientid;
	//List<User> mandatoryDoctors = new ArrayList<User>();
	@Column(name="start")
	String start;
	@Column(name="ending")
	String end;
	
	@Column(name="date")
	String date;
	
	@ManyToOne
	@JoinColumn(name="room")
	Room room;
	
	@Column(name="doctorid")
	Long doctorid;

	public Appointment() {}
	public Appointment(AppointmentDTO appoint) {
		pacientid = appoint.getPacientId();
		start =  appoint.getBegining();
		end = appoint.getEnding();
		date = appoint.getDate();
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




	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
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
	public String getDateStart() {
		return date+"T"+start;
	}
	public String getDateEnd() {
		return date + "T" +end;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(Long doctorid) {
		this.doctorid = doctorid;
	}




	
}
