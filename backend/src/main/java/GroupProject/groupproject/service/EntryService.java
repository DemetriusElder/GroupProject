package GroupProject.groupproject.service;

import GroupProject.groupproject.dto.PostEntryDto;
import GroupProject.groupproject.dto.UpdateEntryDto;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Page<Entry> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        return entryRepository.findAll(pageable);
    }
    
    public long getTableSize() {
    	return entryRepository.count();
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
        if (!entryRepository.existsById(id)) {
            throw new EntryNotFoundException();
        }
        return entryRepository.getById(id);
    }
    
    public Page<Entry> getFilteredEntries(String searchKey, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
    	return entryRepository.getFilteredEntries(searchKey, pageable);
    }
    public Entry updateEntry(UpdateEntryDto updateEntryDto, Long id) throws EntryNotFoundException {
        if (!entryRepository.existsById(id)) {
            throw new EntryNotFoundException();
        }
        Entry existingEntry = entryRepository.getById(id);
        existingEntry.setTitle(updateEntryDto.getTitle());
        existingEntry.setContent(updateEntryDto.getContent());
        existingEntry.setImageUrl(updateEntryDto.getImageUrl());
    	return entryRepository.save(existingEntry);
    }
    public void deleteEntry(Long id) throws EntryNotFoundException {
        if (!entryRepository.existsById(id)) {
            throw new EntryNotFoundException();
        }
    	entryRepository.deleteEntryById(id);
    }
}