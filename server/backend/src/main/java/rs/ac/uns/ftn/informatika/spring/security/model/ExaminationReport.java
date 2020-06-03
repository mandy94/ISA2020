package rs.ac.uns.ftn.informatika.spring.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// POJO koji implementira Spring Security GrantedAuthority kojim se mogu definisati role u aplikaciji
@Entity
@Table(name="EXAMINATIONREPORTS")
public class ExaminationReport {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="details")
    String details;
    
    @Column(name ="user_id")
    private Long user_id;

    @Column(name ="doctor_id")
    private Long doctor_id;

    @Column(name ="user_jmbg")
    private Long user_jmbg;

    @Column(name ="med_id")
    private Long med_id;

    @Column(name ="diagnose_id")
    private Long diagnose_id;
    /*
    @ManyToMany
    @JoinTable(
    		name="diagnosed",
    		  joinColumns = @JoinColumn(name = "examination_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "diagnose_id"))
    private List<Diagnose> diagnosed;
    
    @ManyToMany
    @JoinTable(
    		name="got_medication",
  		  joinColumns = @JoinColumn(name = "examination_id"), 
  		  inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private List<Medicine> med;
    
    */		 
    @ManyToOne
    @JoinColumn(name="user_id")
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	@Override
	public String toString() {
		return "ExaminationReport [id=" + id + ", details=" + details + ", user_id=" + user_id + ", doctor_id="
				+ doctor_id + ", user_jmbg=" + user_jmbg + ", med_id=" + med_id + ", diagnose_id=" + diagnose_id + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Long getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Long getUser_jmbg() {
		return user_jmbg;
	}

	public void setUser_jmbg(Long user_jmbg) {
		this.user_jmbg = user_jmbg;
	}

	public Long getMed_id() {
		return med_id;
	}

	public void setMed_id(Long med_id) {
		this.med_id = med_id;
	}

	public Long getDiagnose_id() {
		return diagnose_id;
	}

	public void setDiagnose_id(Long diagnose_id) {
		this.diagnose_id = diagnose_id;
	}



}
