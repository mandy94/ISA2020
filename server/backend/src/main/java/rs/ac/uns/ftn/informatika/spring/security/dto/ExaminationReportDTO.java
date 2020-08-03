package rs.ac.uns.ftn.informatika.spring.security.dto;

import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.ExaminationReport;
import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;

public class ExaminationReportDTO {

	@Override
	public String toString() {
		return "ExaminationReportDTO [id=" + id + ", details=" + details + ", diagnose=" + diagnose + ", pacient="
				+ pacient + ", pacientid=" + pacientid + ", doctor=" + doctor + ", doctorid=" + doctorid
				+ ", visitDate=" + visitDate + ", visitTime=" + visitTime + ", medication=" + medication
				+ ", therapies=" + therapies + "]";
	}
	private Long id;
	private String details;
	private String diagnose;
	private String pacient;
	private Long pacientid;
	
	private String doctor;
	private Long doctorid;
	private String visitDate;
	private String visitTime;
	private List<Medicine> medication = new ArrayList<Medicine>();
	private List<Therapy> therapies = new ArrayList<Therapy>();
	
	public ExaminationReportDTO() {}
	public ExaminationReportDTO(ExaminationReport report) {
			this.id = report.getId();
			this.details = report.getDetails();
			this.pacient = report.getPacient().getFirstName() + " " + report.getPacient().getLastName();
			this.doctor = report.getDoctor().getFirstName() + " " + report.getDoctor().getLastName();
			this.diagnose = report.getDiagnose().getName();
			this.medication = report.getMedication();
			this.therapies = report.getTherapies();
			this.visitDate = report.getVisitDate();
			this.visitTime = report.getVisitTime();
					
	}
	public Long getPacientid() {
		return pacientid;
	}
	public void setPacientid(Long pacientid) {
		this.pacientid = pacientid;
	}
	public Long getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(Long doctorid) {
		this.doctorid = doctorid;
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
	public List<Medicine> getMedication() {
		return medication;
	}
	public void setMedication(List<Medicine> medication) {
		this.medication = medication;
	}
	public List<Therapy> getTherapies() {
		return therapies;
	}
	public void setTherapies(List<Therapy> therapies) {
		this.therapies = therapies;
	}
	
	public String getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	public String getPacient() {
		return pacient;
	}
	public void setPacient(String pacient) {
		this.pacient = pacient;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
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


	
}
