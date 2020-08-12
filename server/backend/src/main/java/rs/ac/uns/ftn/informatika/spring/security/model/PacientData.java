package rs.ac.uns.ftn.informatika.spring.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PACIENTDATA")
public class PacientData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(mappedBy="data")
    @JsonIgnore
    private User user;
    
//    @Column(name="blood_type")
    @ManyToOne
    private BloodType bloodType;
    
    @Column(name="height")
    private Float height;
    
    @Column(name="weight")
    private Float weight;
    

    @ManyToMany
    @JoinTable(name="pacient_alergies")
    private List<Alergens> alergies = new ArrayList<Alergens>();
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BloodType getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public List<Alergens> getAlergies() {
		return alergies;
	}
	public void setAlergies(List<Alergens> alergies) {
		this.alergies = alergies;
	}
	@Override
	public String toString() {
		return "PacientData [id=" + id + ", user=" + user + ", bloodType=" + bloodType + ", height=" + height
				+ ", weight=" + weight + ", alergies=" + alergies + "]";
	}

    
}
