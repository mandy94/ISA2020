package rs.ac.uns.ftn.informatika.spring.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CookieValue;

@Entity
@Table(name="PRESCRIPTION")
public class Prescription {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	
	@Column(name="pacient")
	Long pacient;
	@Column(name="bar_code")
	String barCode;
	@Column(name="signed")
	Boolean signed;
//	String LBO;
	@Column(name="clinic_details")
	String clinic;//, clinicName;
	
	@Column(name="doctor_id")
	Long doctorId;
	
	@Column(name="nurse_id")
	Long nurseId;
	
	@Column(name="medicine_name")
	String medicineName;
	
	@Column(name="dosage")
	String dosage;
	
	public Prescription() { signed = false;}
	public Prescription(Medicine med) {
		this.medicineName = med.name;
		this.dosage = med.dosage;
		this.signed=false;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPacient() {
		return pacient;
	}
	public void setPacient(Long pacient) {
		this.pacient = pacient;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getNurseId() {
		return nurseId;
	}
	public void setNurseId(Long nurseId) {
		this.nurseId = nurseId;
	}
	public Boolean getSigned() {
		return signed;
	}
	public void setSigned(Boolean signed) {
		this.signed = signed;
	}
	
}