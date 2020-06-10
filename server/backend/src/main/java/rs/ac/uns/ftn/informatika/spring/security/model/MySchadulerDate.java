package rs.ac.uns.ftn.informatika.spring.security.model;

public class MySchadulerDate {

	int h;
	int m;

	public MySchadulerDate(int h, int m) {
		this.h = h;
		this.m = m;
	}
	
	public MySchadulerDate(String time) {
		//something
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}
	
}
