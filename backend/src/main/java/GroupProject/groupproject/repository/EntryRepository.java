package GroupProject.groupproject.repository;

import GroupProject.groupproject.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findAll();

    Entry getById(Long id);

    boolean existsById(Long id);
    
    
    @Query("SELECT e FROM Entry e WHERE CONCAT(e.title, e.author, e.content) LIKE %?1%")
    List<Entry> getFilteredEntries(String searchKey);
}
