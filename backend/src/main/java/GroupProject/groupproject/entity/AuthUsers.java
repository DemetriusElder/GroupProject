package GroupProject.groupproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AuthUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;

	private String password;

	private String role;

	public AuthUsers() {
	}

	public AuthUsers(String username, String pasword, String role) {
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPassword(){
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
	