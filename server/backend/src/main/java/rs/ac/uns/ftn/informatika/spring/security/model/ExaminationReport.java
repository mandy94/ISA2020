
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rs.ac.uns.ftn.informatika.spring.security.dto.ExaminationReportDTO;

@Entity
@Table(name="EXAMINATIONREPORTS")
public class ExaminationReport {


	@Id
    @Column(name="id",columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

	@Column(name="date")
	private String visitDate;
	@Column(name="time")
	private String visitTime;
	
	@Column(name="details")
	private String details;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="diagnose")	
	private Diagnose diagnose;
	
	@ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "examine_medication",
            joinColumns = {  @JoinColumn(name = "examination_id", referencedColumnName = "id")},
            inverseJoinColumns = {  @JoinColumn(name = "medication_id", referencedColumnName = "id")})
	private List<Medicine> medication = new ArrayList<Medicine>();

	
	@Column(name="therapy")
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "examine_therpay",
            joinColumns = {  @JoinColumn(name = "examination_id", referencedColumnName = "id")},
            inverseJoinColumns = {  @JoinColumn(name = "therapy_id", referencedColumnName = "id")})	
	private List<Therapy> therapies = new ArrayList<Therapy>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="is_examined")	
	private User pacient;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="is_doctor")	
	private User doctor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nurce")	
	private User nurce;
	public User getNurce() {
		return nurce;
	}

	public void setNurce(User nurce) {
		this.nurce = nurce;
	}

	public Long getIds() {
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

	public List<Medicine> getMedication() {
		return medication;
	}

	public void setMedication(List<Medicine> medication) {
		this.medication = medication;
	}

	public User getPacient() {
		return pacient;
	}

	public void setPacient(User pacient) {
		this.pacient = pacient;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public Long getId() {
		return id;
	}

	public void copyFromDTO(ExaminationReportDTO reportdto) {
		this.details = reportdto.getDetails();
		
	}



	@Override
	public String toString() {
		return "ExaminationReport [id=" + id + ", visitDate=" + visitDate + ", visitTime=" + visitTime + ", details="
				+ details + ", diagnose=" + diagnose + ", medication=" + medication + ", therapies=" + therapies
				+ ", pacient=" + pacient + ", doctor=" + doctor + "]";
	}

	public List<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<Therapy> therapies) {
		this.therapies = therapies;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public Diagnose getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(Diagnose diagnose) {
		this.diagnose = diagnose;
	}
	
	
}