package GroupProject.groupproject.dto;

public class AuthUsersDto {
	
	private final String username;
    private final String password;
    private final String role;
    
    public AuthUsersDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
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
