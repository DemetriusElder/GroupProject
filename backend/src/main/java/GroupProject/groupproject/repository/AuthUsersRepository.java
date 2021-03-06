package GroupProject.groupproject.repository;

import GroupProject.groupproject.entity.AuthUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthUsersRepository extends JpaRepository<AuthUsers, Long>{
	
	List<AuthUsers> findAll();

	AuthUsers getById(Long id);

	boolean existsById(Long id);

	boolean existsByUsername(String username);

	@Query("SELECT e FROM AuthUsers e WHERE CONCAT(e.username, e.password, e.role) LIKE %?1%")
	List<AuthUsers> getFilteredAuthUsers(String searchKey);

	Optional<AuthUsers> findByUsername(String username);
}
