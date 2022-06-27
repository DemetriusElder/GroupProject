package GroupProject.groupproject.service;

import GroupProject.groupproject.dto.DeletePostDto;
import GroupProject.groupproject.dto.PostEntryDto;
import GroupProject.groupproject.dto.UpdateEntryDto;
import GroupProject.groupproject.entity.AuthUsers;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.entity.Role;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.exception.ForbiddenException;
import GroupProject.groupproject.exception.UsernameNotFoundException;
import GroupProject.groupproject.repository.AuthUsersRepository;
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
    private final AuthUsersRepository authUsersRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository, AuthUsersRepository authUsersRepository) {
        this.entryRepository = entryRepository;
        this.authUsersRepository = authUsersRepository;
    }

    public Page<Entry> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        return entryRepository.findAll(pageable);
    }
    
    public long getTableSize() {
    	return entryRepository.count();
    }

    public void addEntries(PostEntryDto postEntryDto) throws UsernameNotFoundException {

        AuthUsers user = authUsersRepository.findByUsername(postEntryDto.getUsername()).orElseThrow(
                UsernameNotFoundException::new);

        Entry newEntry = new Entry();
        newEntry.setContent(postEntryDto.getContent());
        newEntry.setTitle(postEntryDto.getTitle());
        newEntry.setImageUrl(postEntryDto.getImageUrl());
        newEntry.setDate(LocalDateTime.now());
        newEntry.setAuthor(postEntryDto.getAuthor());
        newEntry.setUser(user);
        entryRepository.save(newEntry);

        // saving words/id into cosmos
        String parse = newEntry.getContent().replaceAll("[\\p{P}&&[^\\u0027]&&[\n]]", "").toLowerCase();
        Long entryId = newEntry.getId();
        String[] arr = parse.split(" ");
        for(String a : arr) {
			
			if (keywordRepo.findByWord(a).isEmpty()) {
				ContentKeyword keyword = new ContentKeyword(a, entryId);
				keywordRepo.save(keyword);
			}
			else {
				Optional<ContentKeyword> opt = keywordRepo.findById(a);
				if(opt.isPresent()) {
					ContentKeyword word = opt.get();
					System.out.println("repo word: "+ word.getWord());
					if(!word.getListOfIds().contains(entryId)) {
						word.addId(entryId);
						keywordRepo.save(word);
					}
				}
			}
		}
        
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
    public Entry updateEntry(UpdateEntryDto updateEntryDto, Long id) throws EntryNotFoundException, UsernameNotFoundException, ForbiddenException {
        AuthUsers user = authUsersRepository.findByUsername(updateEntryDto.getUsername()).orElseThrow(UsernameNotFoundException::new);
        Entry entry = entryRepository.findById(id).orElseThrow(EntryNotFoundException::new);
        if (!user.getUsername().equals(entry.getUser().getUsername())) {
            throw new ForbiddenException();
        }
        Entry existingEntry = entryRepository.getById(id);
        existingEntry.setTitle(updateEntryDto.getTitle());
        existingEntry.setContent(updateEntryDto.getContent());
        existingEntry.setImageUrl(updateEntryDto.getImageUrl());
    	return entryRepository.save(existingEntry);
    }
    public void deleteEntry(Long id, DeletePostDto deletePostDto) throws EntryNotFoundException, UsernameNotFoundException, ForbiddenException {
        AuthUsers user = authUsersRepository.findByUsername(deletePostDto.getUsername()).orElseThrow(UsernameNotFoundException::new);
        Entry entry = entryRepository.findById(id).orElseThrow(EntryNotFoundException::new);
        if (!user.getUsername().equals(entry.getUser().getUsername()) && !user.getRole().equals(Role.ADMIN)) {
            throw new ForbiddenException();
        }
    	entryRepository.deleteById(id);
    }
}