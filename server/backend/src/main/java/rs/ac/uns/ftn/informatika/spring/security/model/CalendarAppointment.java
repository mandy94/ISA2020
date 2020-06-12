package rs.ac.uns.ftn.informatika.spring.security.model;

public class CalendarAppointment {
	public CalendarAppointment(String start, String end, String title) {
		super();
		this.start = start;
		this.end = end;
		this.title = title;
	}
	String start;
	String end;
	String title;
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
