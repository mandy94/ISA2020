package rs.ac.uns.ftn.informatika.spring.security.dto;

import java.util.ArrayList;
import java.util.List;

class AppointmentDetails{
	
	public String start;
	public String ending;
	public Long pacientId;
	public Long jmbg;
	public String pacientFullName;
}
public class AppointmentsPerDay {

	String date;
	List<AppointmentDetails> terms = new ArrayList<>();
	public AppointmentsPerDay() {
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<AppointmentDetails> getTerms() {
		return terms;
	}
	public void setTerms(List<AppointmentDetails> terms) {
		this.terms = terms;
	}
	public void addTerm(String start, String end, Long  pacientFullname) {
		AppointmentDetails details = new AppointmentDetails();
		details.start = start;
		details.ending=end;
		details.pacientId=pacientFullname;
		terms.add(details);
	}
	public void compareTimeAndAdd(String start, String end, Long pacientid, Long jmbg, String pacientFullName) {
		
		for(AppointmentDetails det: this.terms) {
			if(det.start.equals(start))
			{
				det.pacientId = pacientid;
				det.jmbg = jmbg;
				det.pacientFullName=pacientFullName;
			}
		}
		
	}
}
