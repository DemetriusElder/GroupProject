package GroupProject.groupproject.dto;

import GroupProject.groupproject.entity.Role;

public class UserResponseDto {

    private final long id;
    private final String username;
    private final String password;
    private final String fullName;
    private final Role role;

    public UserResponseDto(long id, String username, String password, String fullName, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Role getRole() {
        return role;
    }
}
