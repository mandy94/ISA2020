package rs.ac.uns.ftn.informatika.spring.security.dto;

public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private Long jmbg;
	private String role;
	private String email;
	private boolean enabled;
	public UserDTO(Long id, String firstName, String lastName, String username, Long jmbg, String role, String email,
			boolean enabled) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.jmbg = jmbg;
		this.role = role;
		this.email = email;
		this.enabled = enabled;
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
