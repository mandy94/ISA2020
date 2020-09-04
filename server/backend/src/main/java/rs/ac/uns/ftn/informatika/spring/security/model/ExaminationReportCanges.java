package rs.ac.uns.ftn.informatika.spring.security.model;

import java.util.ArrayList;
import java.util.List;

public class ExaminationReportCanges {
Long id;

private String details;

private Diagnose diagnose;


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


public Diagnose getDiagnose() {
	return diagnose;
}


public void setDiagnose(Diagnose diagnose) {
	this.diagnose = diagnose;
}


public List<Therapy> getTherapies() {
	return therapies;
}


public void setTherapies(List<Therapy> therapies) {
	this.therapies = therapies;
}


private List<Therapy> therapies = new ArrayList<Therapy>();

}
