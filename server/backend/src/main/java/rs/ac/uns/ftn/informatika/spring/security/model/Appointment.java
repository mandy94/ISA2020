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
	
	
	@ManyToOne
	@JoinColumn(name="term")
	SchedulerTime term ;
	
	
	@Column(name="date")
	String date;
	
	@ManyToOne
	@JoinColumn(name="room")
	Room room;
	
	@Column(name="doctorid")
	Long doctorid;

	public Appointment() {}
	public void generateObject(AppointmentDTO appoint) {
		pacientid = appoint.getPacientId();
		doctorid = appoint.getDoctorId();
		term = new SchedulerTime();
		term.setStart(appoint.getBegining());
		term.setEnding(appoint.getEnding());
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


	public SchedulerTime getTerm() {
		return term;
	}
	public void setTerm(SchedulerTime term) {
		this.term = term;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	
	
	public String getDateStart() {
		return date+"T"+ term.getStart();
	}
	public String getDateEnd() {
		return date + "T" + term.getEnding();
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
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", pacientid=" + pacientid + ", term=" + term + ", date=" + date + ", room="
				+ room + ", doctorid=" + doctorid + "]";
	}




	
}
