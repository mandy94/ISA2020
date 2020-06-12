package rs.ac.uns.ftn.informatika.spring.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="OPERATIONROOMS")
public class OperationRoom {

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    
	@Column(name="name")
    String name;
    
    @Column(name="code")
    String code;
    
    @OneToMany(mappedBy="room")
    @Column(name="time")
//    @JsonIgnore
    List<SchedulerTime> workingTime = new ArrayList<SchedulerTime>();
    
    @OneToMany(mappedBy="room")
    @Column(name="scheduler")
    @JsonIgnore
    List<Appointment> scheduler = new ArrayList<Appointment>();

    public OperationRoom() {}
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
	public List getScheduler() {
		return scheduler;
	}
	public void setScheduler(List scheduler) {
		this.scheduler = scheduler;
	}

}
