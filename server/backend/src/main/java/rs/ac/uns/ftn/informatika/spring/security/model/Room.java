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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ROOMS")
public class Room {

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    
	@Column(name="name")
    String name;
    
    @Column(name="code")
    String code;
    
    @Column(name="type")
    String type;
    
    @OneToMany(mappedBy="room")
    @Column(name="time")
    List<SchedulerTime> workingTime = new ArrayList<SchedulerTime>();
    
    @OneToMany(mappedBy="room")
    @Column(name="scheduler")
    @JsonIgnore
    List<Appointment> scheduler = new ArrayList<Appointment>(); 
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "mandatory_doctors",
            joinColumns = {  @JoinColumn(name = "room_id", referencedColumnName = "id")},
            inverseJoinColumns = {  @JoinColumn(name = "user_id", referencedColumnName = "id")})
    List<User> mandatoryDoctors = new ArrayList<User>();
	
    public Room() {}
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public List<SchedulerTime> getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(List<SchedulerTime> workingTime) {
		this.workingTime = workingTime;
	}
	public List<User> getMandatoryDoctors() {
		return mandatoryDoctors;
	}
	public void setMandatoryDoctors(List<User> mandatoryDoctors) {
		this.mandatoryDoctors = mandatoryDoctors;
	}
	public List<Appointment> getScheduler() {
		return scheduler;
	}
	public void setScheduler(List<Appointment> scheduler) {
		this.scheduler = scheduler;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
