package ozguryazilim.movieCollection.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ozguryazilim.movieCollection.entities.concretes.Actor;

public interface ActorDao extends JpaRepository<Actor, Integer> {
	Actor getById(int id);

	List<Actor> getByMovie_Id(int id);

	@Query(value = "SELECT * FROM actors a WHERE a.name LIKE %:keyword% ", nativeQuery = true)
	List<Actor> findActorsByKeyword(@Param("keyword") String keyword);

	Optional<Actor> findByName(String name);

}
