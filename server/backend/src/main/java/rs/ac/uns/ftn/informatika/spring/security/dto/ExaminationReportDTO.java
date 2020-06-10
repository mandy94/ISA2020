package rs.ac.uns.ftn.informatika.spring.security.dto;

import java.util.ArrayList;
import java.util.List;

public class ExaminationReportDTO {

	private Long id;
	private String details;
	private Long pacientid;
	private Long doctorid;
	private List<Long> medication = new ArrayList<Long>();
	private List<Long> therapies = new ArrayList<Long>();
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
	public List<Long> getMedication() {
		return medication;
	}
	public void setMedication(List<Long> medication) {
		this.medication = medication;
	}
	public List<Long> getTherapies() {
		return therapies;
	}
	public void setTherapies(List<Long> therapies) {
		this.therapies = therapies;
	}

	
}
