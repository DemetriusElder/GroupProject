package GroupProject.groupproject.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import GroupProject.groupproject.entity.ContentKeyword;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.repository.AuthUsersRepository;
import GroupProject.groupproject.repository.ContentKeywordRepository;
import GroupProject.groupproject.repository.EntryRepository;
import reactor.core.publisher.Mono;

@Service
public class ContentKeywordService {
	
	private final ContentKeywordRepository contentKeywordRepository;
	//private final EntryRepository entryRepository;
//
	@Autowired
	public ContentKeywordService(ContentKeywordRepository contentKeywordRepository) {
	    this.contentKeywordRepository = contentKeywordRepository;
	    //this.entryRepository = entryRepository;

    }
	
//	List<Long> getIdsFromKeyword(String a){
//		Optional<ContentKeyword> opt = keywordRepo.findById(a);
//		if(opt.isPresent()) {
//			ContentKeyword word = opt.get();
//			return word.getListOfIds();
//		}
//		
//		return null;
//	}
	
	public Optional<ContentKeyword> getById(String a){
		Optional<ContentKeyword> opt = contentKeywordRepository.findById(a);
		return opt;
	}
	
//	public List<Long> getFilteredContentKeywords(String searchKey) {
//		String[] searchWords = searchKey.split(" ");
//		Set <Long> setOfEntries = new LinkedHashSet<Long>();
//		
//		for(int i=0; i<searchWords.length;i++) {
//			Mono<ContentKeyword> monoKeyword = contentKeywordRepository.getByWord(searchWords[i]);
//			ContentKeyword keyword = monoKeyword.block(); 
//			List<Long> listOfIds = keyword.getListOfIds();
//			setOfEntries.addAll(listOfIds);
//			
//		}
//		List<Long> listOfEntries = new ArrayList<Long>(setOfEntries);
////		Iterator<Long> iterator = setOfEntries.iterator();
////		while(iterator.hasNext()) {
////			listOfEntries.add(entryRepository.getById(setOfEntries.iterator().next()));
////		}
//		
//		return listOfEntries;
//		
//	}
	

}
