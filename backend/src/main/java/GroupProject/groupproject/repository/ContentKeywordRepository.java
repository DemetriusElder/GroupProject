package GroupProject.groupproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;

import GroupProject.groupproject.entity.ContentKeyword;

public interface ContentKeywordRepository extends CosmosRepository<ContentKeyword, String>{

	List<ContentKeyword> findByWord(String a);

	ContentKeyword getByWord(String a);
	
//	List<Long> getIdsFromKeyword(String a);


}
