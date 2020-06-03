package rs.ac.uns.ftn.informatika.spring.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

// POJO koji implementira Spring Security GrantedAuthority kojim se mogu definisati role u aplikaciji
@Entity
@Table(name="MEDICATIONS")
public class Medicine{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name")
    String name;
    
    @Column(name="dosage")
    String dosage;
    
    @ManyToMany(mappedBy="medication", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ExaminationReport> reports = new ArrayList<ExaminationReport>();

  
	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name +"]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<ExaminationReport> getReports() {
		return reports;
	}


	public void setReports(List<ExaminationReport> reports) {
		this.reports = reports;
	}


	public String getDosage() {
		return dosage;
	}


	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	

	
}
