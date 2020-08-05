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

@Entity
@Table(name="SCHEDULERTIMES")
public class SchedulerTime {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id",columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

	@Column(name="start")
	String start;
	@Column(name="ending")
	String ending;
	

	@ManyToOne
	@JoinColumn(name="room")
	@JsonIgnore
	Room room;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnding() {
		return ending;
	}

	public void setEnding(String ending) {
		this.ending = ending;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "SchedulerTime [start=" + start + ", ending=" + ending + ", room=" + room + "]";
	}


}