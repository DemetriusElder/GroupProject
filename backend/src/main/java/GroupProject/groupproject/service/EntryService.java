package GroupProject.groupproject.service;

import GroupProject.groupproject.dto.PostEntryDto;
import GroupProject.groupproject.entity.ContentKeyword;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.exception.EntryNotFoundException;
import GroupProject.groupproject.repository.ContentKeywordRepository;
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
    ContentKeywordRepository keywordRepo;
    ContentKeywordService keywordService;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> getAll() {
        return entryRepository.findAll();
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
        System.out.println(id);
        if (!entryRepository.existsById(id)) {
            throw new EntryNotFoundException();
        }
        return entryRepository.getById(id);
    }
    
    public List<Entry> getFilteredEntries(String searchKey){
    	searchKey = searchKey.toLowerCase();
    	return entryRepository.getFilteredEntries(searchKey);
    }
    public Entry updateEntry(Entry entry) {
    	return entryRepository.save(entry);
    }
    public void deleteEntry(Long id) {
    	entryRepository.deleteEntryById(id);
    }
}