package GroupProject.groupproject.dto;

public class LoginAuthUsersDto {
	
	private final String username;
    private final String password;
    
    public LoginAuthUsersDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
