package GroupProject.groupproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import GroupProject.groupproject.entity.ContentKeyword;
import GroupProject.groupproject.service.ContentKeywordService;
import GroupProject.groupproject.service.EntryService;

@RestController("search")
public class ContentKeywordController {
	
	private final ContentKeywordService contentKeywordService;
	
	@Autowired
	public ContentKeywordController( ContentKeywordService contentKeywordService) {
		this.contentKeywordService = contentKeywordService;
	}


//	@GetMapping("/{searchKey}")
//	public String printKeyword (@PathVariable("searchKey") String a) {
//		Optional<ContentKeyword> opt = contentKeywordService.getById(a);
//		if(opt.isPresent()) {
//			ContentKeyword keyword = opt.get();
//			return keyword.getWord();
//		}
//		return null;
//	}
}
