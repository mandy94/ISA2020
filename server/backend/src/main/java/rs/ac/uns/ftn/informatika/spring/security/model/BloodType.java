package rs.ac.uns.ftn.informatika.spring.security.model;

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
@Table(name="BLOODTYPE")
public class BloodType {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column
	private String type;
	
	@OneToMany(mappedBy="bloodType")
	@JsonIgnore
	private List<PacientData> pacient;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<PacientData> getPacient() {
		return pacient;
	}
	public void setPacient(List<PacientData> pacient) {
		this.pacient = pacient;
	}

}
