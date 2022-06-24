package GroupProject.groupproject.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;

import GroupProject.groupproject.entity.ContentKeyword;

public interface ContentKeywordRepository extends CosmosRepository<ContentKeyword, Long>{

}
