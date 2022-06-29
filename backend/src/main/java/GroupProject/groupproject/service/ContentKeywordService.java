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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import GroupProject.groupproject.entity.ContentKeyword;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.repository.AuthUsersRepository;
import GroupProject.groupproject.repository.ContentKeywordRepository;
import GroupProject.groupproject.repository.EntryRepository;
import reactor.core.publisher.Mono;

@Service
public class ContentKeywordService {
	//@Autowired
	private ContentKeywordRepository contentKeywordRepository;
	private final EntryRepository entryRepository;
//
//	public ContentKeywordService() {
////		this.contentKeywordRepository;
//		
//	}
	@Autowired
	public ContentKeywordService(ContentKeywordRepository contentKeywordRepository, EntryRepository entryRepository) {
	    this.contentKeywordRepository = contentKeywordRepository;
	    this.entryRepository = entryRepository;

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
//	
//	public Optional<ContentKeyword> getById(String a){
//		Optional<ContentKeyword> opt = contentKeywordRepository.findById(a);
//		return opt;
//	}
//	
	public Page<Entry> getFilteredContentKeywords(String searchKey, Pageable page) {
		Pageable pageable = page;
		String[] searchWords = searchKey.split(" ");
		List <Long> listOfIds = new ArrayList<Long>();

		for(int i=0; i<searchWords.length;i++) {
			//System.out.println(contentKeywordRepository.findById("ipsum").get());
			Optional<ContentKeyword> opt = contentKeywordRepository.findById(searchWords[i]);

			if(opt.isPresent()) {
				ContentKeyword keyword = opt.get();
				System.out.println("******** "+keyword.getWord()+" ***********");
				List<Long> listOfIds2 = keyword.getListOfIds();
				if(i==0) {
					listOfIds = listOfIds2;
				}
				else {
					listOfIds.retainAll(listOfIds2);
				}
			}
			
		}
		System.out.println("********* "+listOfIds+" *********");
		List<Entry> listOfEntries = new ArrayList<Entry>();
		Iterator<Long> iterator = listOfIds.iterator();
		for(Long l : listOfIds) {
			listOfEntries.add(entryRepository.getById(l));
		}
		Page<Entry> searchPage = new PageImpl<>(listOfEntries, pageable, listOfEntries.size());
		return searchPage;
		
	}
	

}
