package rs.ac.uns.ftn.informatika.spring.security.dto;

import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.spring.security.model.Medicine;
import rs.ac.uns.ftn.informatika.spring.security.model.Therapy;

public class ExaminationReportDTO {

	private Long id;
	private String details;
	private Long pacientid;
	private Long doctorid;
	private List<Medicine> medication = new ArrayList<Medicine>();
	private List<Therapy> therapies = new ArrayList<Therapy>();
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
	@Override
	public String toString() {
		return "ExaminationReportDTO [id=" + id + ", details=" + details + ", pacientid=" + pacientid + ", doctorid="
				+ doctorid + "]";
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


	
}
