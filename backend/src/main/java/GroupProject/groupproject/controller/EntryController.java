package GroupProject.groupproject.controller;

import java.util.List;

import GroupProject.groupproject.dto.PostEntryDto;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
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
}