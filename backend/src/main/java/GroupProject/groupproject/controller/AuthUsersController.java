package GroupProject.groupproject.controller;

import java.util.List;

import GroupProject.groupproject.dto.LoginAuthUsersDto;
import GroupProject.groupproject.dto.RegisterAuthUsersDto;
import GroupProject.groupproject.dto.UserResponseDto;
import GroupProject.groupproject.entity.AuthUsers;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.exception.UserAlreadyExistException;
import GroupProject.groupproject.service.AuthUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController()
@RequestMapping("auth-users")
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

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public void postAuthUsers(@Valid @RequestBody RegisterAuthUsersDto registerAuthUsersDto) throws UserAlreadyExistException {
		authusersService.addAuthUsers(registerAuthUsersDto);
	}

	@PostMapping("/login")
	public UserResponseDto login(@RequestBody LoginAuthUsersDto loginAuthUsersDto) throws UserAlreadyExistException {
		return authusersService.login(loginAuthUsersDto);
	}

	// make this long so no one can find this endpoint
	@PostMapping("/this-is-an-endpoint-to-create-admin-hehe")
	public void postAdmin(@Valid @RequestBody RegisterAuthUsersDto registerAuthUsersDto) throws UserAlreadyExistException {
		authusersService.addAddmin(registerAuthUsersDto);
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

//	@CrossOrigin(origins = "http://localhost:4200")
//	@Transactional
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteEntry(@PathVariable("id") Long id){
//		authusersService.deleteUsers(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
}