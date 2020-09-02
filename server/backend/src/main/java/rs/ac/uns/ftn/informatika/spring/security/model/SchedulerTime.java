package rs.ac.uns.ftn.informatika.spring.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SCHEDULERTIMES")
public class SchedulerTime {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id",columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

	@Column(name="start")
	String start;
	@Column(name="ending")
	String ending;
	

    @OneToMany(mappedBy="term")
    private List<Appointment> appointments = new ArrayList<>();
	

	@ManyToMany(mappedBy="timeTable", fetch = FetchType.LAZY)
	@JsonIgnore
    List<User> doctors = new ArrayList<User>();
	

	public List<User> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<User> doctors) {
		this.doctors = doctors;
	}

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




}