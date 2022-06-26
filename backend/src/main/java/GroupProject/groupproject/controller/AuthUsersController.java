package GroupProject.groupproject.controller;

import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import GroupProject.groupproject.dto.AuthUsersDto;
import GroupProject.groupproject.entity.AuthUsers;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.service.AuthUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
	public int postAuthUsers(@RequestBody AuthUsersDto authusersDto) throws EntryNotFoundException {
		int x = 0;
		try {
			authusersService.getByUsername(authusersDto.getusername()).getUsername();
		}catch(EntryNotFoundException e) {
			x++;
		}
		catch(NonUniqueResultException e) {
			x++;
		}
		catch(IncorrectResultSizeDataAccessException e) {
			x++;
		}
		if (x == 1) {
			authusersService.addAuthUsers(authusersDto);
		}
		return x;
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

	@CrossOrigin(origins = "http://localhost:4200")
	@Transactional
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEntry(@PathVariable("id") Long id){
		authusersService.deleteUsers(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}