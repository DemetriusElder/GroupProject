package GroupProject.groupproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import GroupProject.groupproject.dto.DeletePostDto;
import GroupProject.groupproject.dto.PostEntryDto;
import GroupProject.groupproject.dto.UpdateEntryDto;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.exception.ForbiddenException;
import GroupProject.groupproject.exception.UsernameNotFoundException;
import GroupProject.groupproject.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;

@RestController()
@RequestMapping("entries")
public class EntryController {

	private final EntryService entryService;

	@Autowired
	public EntryController(EntryService entryService) {
		this.entryService = entryService;
	}

	@GetMapping()
	public ResponseEntity<Page<Entry>> getEntries(@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<>(entryService.getAll(page, size), HttpStatus.OK);
	}

//	@GetMapping("/count")
//	public ResponseEntity<Long> getEntriesCount() {
//		return new ResponseEntity<Long>(entryService.getTableSize() , HttpStatus.OK);
//	}

//	@GetMapping("/pagelist/{pagenumber}")
//	public List<Entry> getPaginatedList(@PathVariable("pagenumber")int pagenumber) {
//		int pagesize = 6;
//		int x = pagesize * (pagenumber -1);
//		List<Entry> tempList = entryService.getAll();
//		Collections.reverse(tempList);
//		List<Entry> pageList = new ArrayList<Entry>(pagesize);
//		for(int i = 0; i < pagesize; i++) {
//			if(x < tempList.size()) {
//				pageList.add(tempList.get(x));
//				x++;
//			}
//		}
//		Collections.reverse(pageList);
//		return pageList;
//	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public void postEntry(@RequestBody PostEntryDto postEntryDto) throws UsernameNotFoundException {
		entryService.addEntries(postEntryDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Entry> getEntryById(@PathVariable("id") Long id) throws EntryNotFoundException {
		Entry newEntry = entryService.getById(id);
		return new ResponseEntity<>(newEntry, HttpStatus.OK);
	}
	
//	@GetMapping("/search/{searchKey}")
//	public List<Entry> getFiltered(@PathVariable("searchKey") String key){
//		return entryService.getFilteredEntries(key);
//	}

	@GetMapping("/search")
	public ResponseEntity<Page<Entry>> searchEntries(@RequestParam String key,
													 @RequestParam int page,
													 @RequestParam int size) {
		return new ResponseEntity<>(entryService.getFilteredEntries(key, page, size), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Entry> updateEntry(@Valid @RequestBody UpdateEntryDto updateEntryDto,
											 @PathVariable Long id) throws EntryNotFoundException, UsernameNotFoundException, ForbiddenException {
		Entry updateEntry = entryService.updateEntry(updateEntryDto, id);
		return new ResponseEntity<>(updateEntry, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEntry(@RequestBody DeletePostDto deletePostDto,
											@PathVariable Long id)
														throws EntryNotFoundException,
														UsernameNotFoundException,
														ForbiddenException {
		entryService.deleteEntry(id, deletePostDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}