package GroupProject.groupproject.controller;

import java.util.ArrayList;
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
	@GetMapping("/count")
	public ResponseEntity<Long> getEntriesCount() {
		return new ResponseEntity<Long>(entryService.getTableSize() , HttpStatus.OK); 
	}

	@GetMapping("/pagelist/{pagenumber}")
	public List<Entry> getPaginatedList(@PathVariable("pagenumber")int pagenumber) {
		int pagesize = 6;
		int x = pagesize * (pagenumber -1);
		List<Entry> tempList = entryService.getAll();
		List<Entry> pageList = new ArrayList<Entry>(pagesize);
		for(int i = 0; i < pagesize; i++) {
			if(x < tempList.size()) {
				pageList.add(tempList.get(x));
				x++;
			}
		}
		return pageList;
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
	@PutMapping
	public ResponseEntity<Entry> updateEntry(@RequestBody Entry entry){
		Entry updateEntry = entryService.updateEntry(entry);
		return new ResponseEntity<>(updateEntry, HttpStatus.OK);
	}
	@Transactional
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEntry(@PathVariable("id") Long id){
		entryService.deleteEntry(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
