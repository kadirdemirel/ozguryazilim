package ozguryazilim.movieCollection;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import ozguryazilim.movieCollection.dataAccess.abstracts.MovieDao;

import ozguryazilim.movieCollection.entities.concretes.Movie;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieDaoTests {

	@Autowired
	MovieDao _movieDao;

	@Test
	@Rollback(value = false)
	@Order(1)
	public void saveMovieTest() {

		Movie movie = Movie.builder().movieName("Cehennem Melekleri").media(
				"https://img-puhutv.mncdn.com/media/img/1920x1080/21-03/12/expendables-2title_16x9-1615499273.jpg")
				.build();

		_movieDao.save(movie);

		Assertions.assertThat(movie.getId()).isGreaterThan(0);

	}

	@Test
	@Order(2)
	public void getListMoviesTest() {
		List<Movie> movieList = _movieDao.findAll();
		Assertions.assertThat(movieList.size()).isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void getMovieTest() {

		Movie movie = _movieDao.findById(1).get();
		Assertions.assertThat(movie.getId()).isEqualTo(1);
	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateMovieTest() {
		Movie movie = _movieDao.findById(1).get();
		movie.setMovieName("Cehennem Melekleri2");
		movie.setDescription("Aksiyon Filmi");
		movie.setType("Aksiyon-Macera");
		movie.setMedia("media");

		Movie movieUpdate = _movieDao.save(movie);

		Assertions.assertThat(movieUpdate.getMovieName()).isEqualTo("Cehennem Melekleri2");
	}

	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteMovieTest() {
		Movie movie = _movieDao.findById(1).get();

		_movieDao.delete(movie);

		Movie movie2 = null;

		Optional<Movie> optionalMovie = _movieDao.findByMovieName("Cehennem Melekleri2");

		if (optionalMovie.isPresent())
			movie2 = optionalMovie.get();

		Assertions.assertThat(movie2).isNull();
	}

}
