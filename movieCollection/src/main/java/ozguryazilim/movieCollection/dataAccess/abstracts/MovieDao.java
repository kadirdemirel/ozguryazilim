package ozguryazilim.movieCollection.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ozguryazilim.movieCollection.entities.concretes.Movie;

public interface MovieDao extends JpaRepository<Movie, Integer> {

	@Query(value = "SELECT * FROM movies m WHERE m.movie_name LIKE %:keyword% OR m.type LIKE %:keyword%", nativeQuery = true)
	List<Movie> findMoviesByKeyword(@Param("keyword") String keyword);

	Optional<Movie> findByMovieName(String movieName);

}
