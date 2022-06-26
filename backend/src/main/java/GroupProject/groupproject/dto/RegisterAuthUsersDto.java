package GroupProject.groupproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterAuthUsersDto {

    @NotBlank(message = "Full name cannot be null")
    private final String fullName;

    @Size(min = 6, message = "Username must be at least 6 characters")
    private final String username;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private final String password;

    public RegisterAuthUsersDto(String username, String password, String fullName) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
