package GroupProject.groupproject.controller;

import java.util.List;

import GroupProject.groupproject.dto.AuthUsersDto;
import GroupProject.groupproject.entity.AuthUsers;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.service.AuthUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("authusers")
public class AuthUsersController {

	private final AuthUsersService authusersService;

	@Autowired
	public AuthUsersController(AuthUsersService authusersService) {
		this.authusersService = authusersService;
	}

	@GetMapping()
	public List<AuthUsers> getAuthUsers() {
		return authusersService.getAll();
	}

	@PostMapping()
	public void postAuthUsers(@RequestBody AuthUsersDto authusersDto) {
		authusersService.addAuthUsers(authusersDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthUsers> getAuthUsersById(@PathVariable("id") Long id) throws EntryNotFoundException {
		AuthUsers newAuthUsers = authusersService.getById(id);
		return new ResponseEntity<>(newAuthUsers, HttpStatus.OK);
	}
	@GetMapping("/usernamesearch/{username}")
	public ResponseEntity<AuthUsers> getAuthUsersByUsername(@PathVariable("username") String username) throws EntryNotFoundException {
		AuthUsers newAuthUsers = authusersService.getByUsername(username);
		return new ResponseEntity<>(newAuthUsers, HttpStatus.OK);
	}
	
	@GetMapping("/search/{searchKey}")
	public List<AuthUsers> getFiltered(@PathVariable("searchKey") String key){
		return authusersService.getFilteredAuthUsers(key);
	}
}