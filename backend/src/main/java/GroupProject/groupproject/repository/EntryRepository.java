package GroupProject.groupproject.repository;

import GroupProject.groupproject.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findAll();
    Entry getById(Long id);

}
