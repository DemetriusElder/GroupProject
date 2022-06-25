package GroupProject.groupproject.repository;

import GroupProject.groupproject.entity.Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    Page<Entry> findAll(Pageable pageable);

    Entry getById(Long id);

    boolean existsById(Long id);
    
    void deleteEntryById(Long id);
    
    @Query("SELECT e FROM Entry e WHERE LOWER(CONCAT(e.title, e.author, e.content)) LIKE %?1%")
    Page<Entry> getFilteredEntries(String searchKey, Pageable pageable);
}
