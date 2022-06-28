package GroupProject.groupproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;

import GroupProject.groupproject.entity.ContentKeyword;
import GroupProject.groupproject.entity.Entry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ContentKeywordRepository extends CosmosRepository<ContentKeyword, String>{

	//List<ContentKeyword> findByWord(String a);

//	Mono<ContentKeyword> getByWord(String a);
//	
//    List<Long> getFilteredContentKeywords(String a);
	
//	void addWordAndIdToCosmos(String words, long blogId);
//	List<Long> getIdsFromKeyword(String a);


}
