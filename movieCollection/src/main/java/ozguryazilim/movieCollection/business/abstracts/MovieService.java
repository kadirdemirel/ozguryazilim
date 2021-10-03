package ozguryazilim.movieCollection.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;

import ozguryazilim.movieCollection.entities.concretes.Movie;

public interface MovieService {
	List<Movie> getAll();

	void add(Movie movie);

	Movie getMovieById(int id);

	void deleteMovieById(int id);

	List<Movie> getSearchMovie(String search);

	Page<Movie> findPage(int pageNo, int pageSize, String sortField, String sortDirection);

	

}
