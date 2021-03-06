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

// POJO koji implementira Spring Security GrantedAuthority kojim se mogu definisati role u aplikaciji
@Entity
@Table(name="DIAGNOSES")
public class Diagnose  {


	@Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id;

    @Column(name="name")
    String name;
    
    @Column(name="code")
    String code;
    
    @OneToMany(mappedBy = "diagnose")
    @JsonIgnore
   private List<ExaminationReport> report = new ArrayList<>();
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	@Override
	public String toString() {
		return "Diagnose [id=" + id + ", name=" + name + ", code=" + code + "]";
	}

	public List<ExaminationReport> getReport() {
		return report;
	}

	public void setReport(List<ExaminationReport> report) {
		this.report = report;
	}

}
