package GroupProject.groupproject.controller;

import java.util.List;

import GroupProject.groupproject.dto.PostEntryDto;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("entries")
public class EntryController {

	private final EntryService entryService;

	@Autowired
	public EntryController(EntryService entryService) {
		this.entryService = entryService;
	}

	@GetMapping()
	public List<Entry> getEntries() {
		return entryService.getAll();
	}

	@PostMapping()
	public void postEntry(@RequestBody PostEntryDto postEntryDto) {
		entryService.addEntries(postEntryDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Entry> getEntryById(@PathVariable("id") Long id) throws EntryNotFoundException {
		Entry newEntry = entryService.getById(id);
		return new ResponseEntity<>(newEntry, HttpStatus.OK);
	}
	
	@GetMapping("/search/{searchKey}")
	public List<Entry> getFiltered(@PathVariable("searchKey") String key){
		return entryService.getFilteredEntries(key);
	}
}