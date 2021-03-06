package GroupProject.groupproject.service;

import GroupProject.groupproject.dto.DeletePostDto;
import GroupProject.groupproject.dto.PostEntryDto;
import GroupProject.groupproject.dto.UpdateEntryDto;
import GroupProject.groupproject.entity.AuthUsers;
import GroupProject.groupproject.entity.ContentKeyword;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.entity.Role;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.exception.ForbiddenException;
import GroupProject.groupproject.exception.UsernameNotFoundException;
import GroupProject.groupproject.repository.AuthUsersRepository;
import GroupProject.groupproject.repository.ContentKeywordRepository;
import GroupProject.groupproject.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EntryService {

    private final EntryRepository entryRepository;
    private final AuthUsersRepository authUsersRepository;
    private final ContentKeywordService contentKeywordService;
    private final ContentKeywordRepository contentKeywordRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository, AuthUsersRepository authUsersRepository, ContentKeywordService contentKeywordService, ContentKeywordRepository contentKeywordRepository) {
        this.entryRepository = entryRepository;
        this.authUsersRepository = authUsersRepository;
        this.contentKeywordService = contentKeywordService;
        this.contentKeywordRepository = contentKeywordRepository;
        
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
        String[] arr = parse.split(" ");
        Set<String> set = new HashSet<String>(Arrays.asList(arr));
	      for(String s : set) {
				
				if (!contentKeywordRepository.findByWord(s).isEmpty()) {
					Optional<ContentKeyword> opt = contentKeywordRepository.findById(s);
					if(contentKeywordRepository.findById(s).isPresent()) {
						ContentKeyword word = opt.get();
						if(!word.getListOfIds().contains(newEntry.getId())) {
							word.addId(newEntry.getId());
							contentKeywordRepository.save(word);
						}
					}
				}
				else {
					ContentKeyword keyword = new ContentKeyword(s, newEntry.getId());
					contentKeywordRepository.save(keyword);
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
//      Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
//    	return entryRepository.getFilteredEntries(searchKey, pageable);
    	Page<Entry> searchPage = contentKeywordService.getFilteredContentKeywords(searchKey, page, size);
//    	Page<Entry> searchPage = new PageImpl<>(list.setPage(page).getPageList());
//    	Page<Entry> searchPage = new PageImpl<>(list, pageable, list.size());
    	return searchPage;
//    	return contentKeywordService.getFilteredContentKeywords(searchKey, pageable);
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