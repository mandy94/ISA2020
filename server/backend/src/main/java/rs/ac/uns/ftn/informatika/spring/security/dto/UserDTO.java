package rs.ac.uns.ftn.informatika.spring.security.dto;

import rs.ac.uns.ftn.informatika.spring.security.model.PacientData;
import rs.ac.uns.ftn.informatika.spring.security.model.User;

public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private Long jmbg;
	private String role;
	private String email;
	private boolean enabled;
	private PacientData data;
	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.jmbg = user.getJmbg();
		this.role = user.getRole();
		this.email = user.getEmail();
		this.enabled = user.isEnabled();
		this.data = user.getData();
	}
	public PacientData getData() {
		return data;
	}
	public void setData(PacientData data) {
		this.data = data;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getJmbg() {
		return jmbg;
	}
	public void setJmbg(Long jmbg) {
		this.jmbg = jmbg;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

}
