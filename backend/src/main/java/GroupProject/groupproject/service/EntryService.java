package GroupProject.groupproject.service;

import GroupProject.groupproject.dto.PostEntryDto;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> getAll() {
        return entryRepository.findAll();
    }

    public void addEntries(PostEntryDto postEntryDto) {
        Entry newEntry = new Entry();
        newEntry.setAuthor(postEntryDto.getAuthor());
        newEntry.setContent(postEntryDto.getContent());
        newEntry.setTitle(postEntryDto.getTitle());
        newEntry.setImageUrl(postEntryDto.getImageUrl());
        newEntry.setDate(LocalDateTime.now());
        entryRepository.save(newEntry);
    }
    public Entry getById(Long id) throws EntryNotFoundException {
        System.out.println(id);
        if (!entryRepository.existsById(id)) {
            throw new EntryNotFoundException();
        }
        return entryRepository.getById(id);
    }
    
    public List<Entry> getFilteredEntries(String searchKey){
    	return entryRepository.getFilteredEntries(searchKey);
    }
}