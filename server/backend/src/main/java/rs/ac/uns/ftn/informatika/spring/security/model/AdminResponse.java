package rs.ac.uns.ftn.informatika.spring.security.model;

public class AdminResponse {

	String descripton;
	String email;
	@Override
	public String toString() {
		return "AdminResponse [descripton=" + descripton + ", email=" + email + "]";
	}
	public String getDescripton() {
		return descripton;
	}
	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
