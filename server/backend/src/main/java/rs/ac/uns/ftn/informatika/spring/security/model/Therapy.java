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
@Table(name="THERAPIES")
public class Therapy  {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id",columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name")
    String name;
    
    @ManyToMany(mappedBy="therapies", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ExaminationReport> reports = new ArrayList<ExaminationReport>();

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

}
