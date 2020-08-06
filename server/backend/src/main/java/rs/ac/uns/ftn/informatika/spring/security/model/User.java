package rs.ac.uns.ftn.informatika.spring.security.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

// POJO koji implementira Spring Security UserDetails interfejs koji specificira
// osnovne osobine Spring korisnika (koje role ima, da li je nalog zakljucan, istekao, da li su kredencijali istekli)
@Entity
@Table(name="USERS")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name="birthdate")
    private String birthdate;
    
    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "jmbg")
    private Long jmbg;
    
    @Column(name ="role")
    private String role;
    
	@Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;
    
    @Column(name="status")
    private String status;
    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;
    
    @OneToOne
    @JoinColumn(name="pacient_data")
    private PacientData data;
    
    public PacientData getData() {
		return data;
	}

	public void setData(PacientData data) {
		this.data = data;
	}

	@ManyToMany(mappedBy="mandatoryDoctors", fetch = FetchType.LAZY)
    @JsonIgnore
    List<Room> dedicatedRoom = new ArrayList<Room>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;
    
    @OneToMany(mappedBy = "pacient")
    @JsonIgnore
    private List<ExaminationReport> reports = new ArrayList<ExaminationReport>();
    
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<ExaminationReport> visits=new ArrayList<ExaminationReport>(); // same as report but from doctor side of view


    public List<ExaminationReport> getReports() {
		return reports;
	}

	public void setReports(List<ExaminationReport> reports) {
		this.reports = reports;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
        this.password = password;
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

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	
	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public List<Room> getDedicatedRoom() {
		return dedicatedRoom;
	}

	public void setDedicatedRoom(List<Room> dedicatedRoom) {
		this.dedicatedRoom = dedicatedRoom;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ExaminationReport> getVisits() {
		return visits;
	}

	public void setVisits(List<ExaminationReport> visits) {
		this.visits = visits;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFullName() {
	return firstName + " " + lastName;
	}

}
