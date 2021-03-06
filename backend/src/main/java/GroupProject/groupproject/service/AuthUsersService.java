package GroupProject.groupproject.service;

import GroupProject.groupproject.dto.LoginAuthUsersDto;
import GroupProject.groupproject.dto.RegisterAuthUsersDto;
import GroupProject.groupproject.dto.UserResponseDto;
import GroupProject.groupproject.entity.AuthUsers;
import GroupProject.groupproject.entity.Role;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.exception.UserAlreadyExistException;
import GroupProject.groupproject.repository.AuthUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuthUsersService {

    private final AuthUsersRepository authUsersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthUsersService(AuthUsersRepository authUsersRepository, PasswordEncoder passwordEncoder) {
        this.authUsersRepository = authUsersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AuthUsers> getAll() {
        return authUsersRepository.findAll();
    }

    // THIS IS TO ADD NORMIE FOR UI
    public void addAuthUsers(RegisterAuthUsersDto registerAuthUsersDto) throws UserAlreadyExistException {
        addUserBase(registerAuthUsersDto, Role.NORMIE);
    }
    public AuthUsers getById(Long id) throws EntryNotFoundException {
        if (!authUsersRepository.existsById(id)) {
            throw new EntryNotFoundException();
        }
        return authUsersRepository.getById(id);
    }
    public AuthUsers getByUsername(String username) throws EntryNotFoundException {
        System.out.println(username);
        if (!authUsersRepository.existsByUsername(username)) {
            throw new EntryNotFoundException();
        }
        return authUsersRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
    
    public List<AuthUsers> getFilteredAuthUsers(String searchKey){
    	return authUsersRepository.getFilteredAuthUsers(searchKey);
    }

    // THIS IS TO ADD ADMIN NOT EXPOSED TO FRONTEND
    public void addAddmin(RegisterAuthUsersDto registerAuthUsersDto) throws UserAlreadyExistException {
        addUserBase(registerAuthUsersDto, Role.ADMIN);
    }

    public void addUserBase(RegisterAuthUsersDto registerAuthUsersDto, Role role) throws UserAlreadyExistException {
        AuthUsers authUsers = new AuthUsers();
        if (authUsersRepository.existsByUsername(registerAuthUsersDto.getUsername())) {
            throw new UserAlreadyExistException();
        }
        authUsers.setUsername(registerAuthUsersDto.getUsername());
        authUsers.setPassword(passwordEncoder.encode(registerAuthUsersDto.getPassword()));
        authUsers.setFullName(registerAuthUsersDto.getFullName());
        authUsers.setRole(role);
        authUsersRepository.save(authUsers);
    }

    public UserResponseDto login(LoginAuthUsersDto loginAuthUsersDto) {

        AuthUsers users = authUsersRepository
                .findByUsername(loginAuthUsersDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(""));

        return new UserResponseDto(users.getId(),
                                   users.getUsername(),
                                   loginAuthUsersDto.getPassword(),
                                   users.getFullName(),
                                   users.getRole());

    }

//    public void deleteUsers(Long id) {
//        authUsersRepository.deleteUsersById(id);
//    }
}