package GroupProject.groupproject.service;

import GroupProject.groupproject.dto.PostEntryDto;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public Entry getById(Long id) throws ResourceNotFoundException(){
    	return entryRepository.getById(id);
    }
}