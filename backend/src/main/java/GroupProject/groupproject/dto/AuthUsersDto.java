package GroupProject.groupproject.dto;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class AuthUsersDto {
	
	private final String username;
    private final String password;
    private final String role;
    String salt = BCrypt.gensalt(12);
    
    public AuthUsersDto(String username, String password, String role) {
        this.username = username;
        this.password = BCrypt.hashpw(password, salt);
        this.role = role;
    }

    public String getusername() {
        return username;
    }

    public String getpassword() {
        return password;
    }

    public String getrole() {
        return role;
    }

}
