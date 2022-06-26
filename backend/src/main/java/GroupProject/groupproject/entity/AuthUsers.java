package GroupProject.groupproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AuthUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;

	private String password;

	private String fullName;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Entry> entries;

	private Role role;

	public AuthUsers() {
	}

	public AuthUsers(String username, String password, String fullName, List<Entry> entries, Role role) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.entries = entries;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
	