package GroupProject.groupproject.service;

import GroupProject.groupproject.dto.AuthUsersDto;
import GroupProject.groupproject.entity.AuthUsers;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.repository.AuthUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthUsersService {

    private final AuthUsersRepository authusersRepository;

    @Autowired
    public AuthUsersService(AuthUsersRepository authusersRepository) {
        this.authusersRepository = authusersRepository;
    }

    public List<AuthUsers> getAll() {
        return authusersRepository.findAll();
    }

    public void addAuthUsers(AuthUsersDto authusersDto) {
        AuthUsers authusers = new AuthUsers();
        authusers.setUsername(authusersDto.getusername());
        authusers.setPassword(authusersDto.getpassword());
        authusers.setRole(authusersDto.getrole());
        authusersRepository.save(authusers);
    }
    public AuthUsers getById(Long id) throws EntryNotFoundException {
        if (!authusersRepository.existsById(id)) {
            throw new EntryNotFoundException();
        }
        return authusersRepository.getById(id);
    }
    public AuthUsers getByUsername(String username) throws EntryNotFoundException {
        System.out.println(username);
        if (!authusersRepository.existsByUsername(username)) {
            throw new EntryNotFoundException();
        }
        return authusersRepository.getByUsername(username);
    }
    
    public List<AuthUsers> getFilteredAuthUsers(String searchKey){
    	return authusersRepository.getFilteredAuthUsers(searchKey);
    }
}