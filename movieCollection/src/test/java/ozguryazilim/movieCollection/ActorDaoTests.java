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

import ozguryazilim.movieCollection.dataAccess.abstracts.ActorDao;
import ozguryazilim.movieCollection.entities.concretes.Actor;

import ozguryazilim.movieCollection.entities.concretes.Movie;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActorDaoTests {

	@Autowired
	ActorDao _actorDao;

	@Test
	@Rollback(value = false)
	@Order(1)
	public void saveActorTest() {
		Actor actor = Actor.builder().name("Tolga").lastName("Çevik").role("Başrol").build();

		_actorDao.save(actor);

		Assertions.assertThat(actor.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getListActorsTest() {
		List<Actor> actorList = _actorDao.findAll();
		Assertions.assertThat(actorList.size()).isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void getActorTest() {

		Actor actor = _actorDao.findById(1).get();
		Assertions.assertThat(actor.getId()).isEqualTo(1);
	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateActorTest() {
		Movie movie = new Movie();
		movie.setMovieName("Cehennem Melekleri2");
		movie.setDescription("Aksiyon Filmi");
		movie.setType("Aksiyon-Macera");
		movie.setMedia("media");

		Actor actor = _actorDao.findById(1).get();
		
		actor.setName("Cem");
		actor.setLastName("Yılmaz");
		actor.setRole("Başrol");
		actor.setMovie(movie);

		Actor actorUpdate = _actorDao.save(actor);

		Assertions.assertThat(actorUpdate.getName()).isEqualTo("Cem");
	}

	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteActorTest() {
		Actor actor = _actorDao.findById(1).get();

		_actorDao.delete(actor);

		Actor actor2 = null;

		Optional<Actor> optionalActor = _actorDao.findByName("Cem");

		if (optionalActor.isPresent())
			actor2 = optionalActor.get();

		Assertions.assertThat(actor2).isNull();
	}
}
