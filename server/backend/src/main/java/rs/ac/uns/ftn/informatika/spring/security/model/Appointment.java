package rs.ac.uns.ftn.informatika.spring.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rs.ac.uns.ftn.informatika.spring.security.dto.AppointmentDTO;

@Entity
@Table(name="APPOINTMENTS")
public class Appointment {
	Long pacientid;
	//List<User> mandatoryDoctors = new ArrayList<User>();
	
	String begining, ending;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name="room")
//	@JsonIgnore
	OperationRoom room;

	public Appointment() {}
	public Appointment(AppointmentDTO appoint) {
	pacientid = appoint.getPacientId();
	begining =  appoint.getBegining();
	ending = appoint.getEnding();
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

	public OperationRoom getRoom() {
		return room;
	}

	public void setRoom(OperationRoom room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Appointment [pacientid=" + pacientid + ", begining=" + begining + ", ending=" + ending + ", id=" + id
				+ ", room=" + room + "]";
	}




	
}
