package ozguryazilim.movieCollection.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ozguryazilim.movieCollection.business.abstracts.MovieService;
import ozguryazilim.movieCollection.dataAccess.abstracts.MovieDao;
import ozguryazilim.movieCollection.entities.concretes.Movie;

@Service
public class MovieManager implements MovieService {
	@Autowired
	MovieDao _movieDao;

	@Override
	public List<Movie> getAll() {
		return _movieDao.findAll();
	}

	@Override
	public void add(Movie movie) {

		_movieDao.save(movie);

	}

	@Override
	public Movie getMovieById(int id) {
		Optional<Movie> optional = _movieDao.findById(id);
		Movie movie = null;
		if (optional.isPresent()) {
			movie = optional.get();
		} else {
			throw new RuntimeException("Movie not found for id ::" + id);
		}
		return movie;
	}

	@Override
	public void deleteMovieById(int id) {

		_movieDao.deleteById(id);

	}

	@Override
	public List<Movie> getSearchMovie(String search) {
		List<Movie> searchMovie = _movieDao.findMoviesByKeyword(search);
		return searchMovie;
	}

	@Override
	public Page<Movie> findPage(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return _movieDao.findAll(pageable);
	}

}
