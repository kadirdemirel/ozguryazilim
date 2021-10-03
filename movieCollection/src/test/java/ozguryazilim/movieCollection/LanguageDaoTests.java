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

import ozguryazilim.movieCollection.dataAccess.abstracts.LanguageDao;
import ozguryazilim.movieCollection.entities.concretes.Language;
import ozguryazilim.movieCollection.entities.concretes.Movie;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LanguageDaoTests {

	@Autowired
	LanguageDao _languageDao;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveLanguageTest() {
		Language language = Language.builder().languageName("Japonca").build();

		_languageDao.save(language);

		Assertions.assertThat(language.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getListLanguagesTest() {
		List<Language> languageList = _languageDao.findAll();
		Assertions.assertThat(languageList.size()).isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void getLanguageTest() {

		Language language = _languageDao.findById(1).get();
		Assertions.assertThat(language.getId()).isEqualTo(1);
	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateLanguageTest() {
		Movie movie = new Movie();
		movie.setMovieName("Cehennem Melekleri2");
		movie.setDescription("Aksiyon Filmi");
		movie.setType("Aksiyon-Macera");
		movie.setMedia("media");

		Language language = _languageDao.findById(1).get();
		language.setLanguageName("Çince");
		language.setMovie(movie);

		Language languageUpdate = _languageDao.save(language);

		Assertions.assertThat(languageUpdate.getLanguageName()).isEqualTo("Çince");
	}

	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteLanguageTest() {
		Language language = _languageDao.findById(1).get();

		_languageDao.delete(language);

		Language language2 = null;

		Optional<Language> optionalLanguage = _languageDao.findByLanguageName("Çince");

		if (optionalLanguage.isPresent())
			language2 = optionalLanguage.get();

		Assertions.assertThat(language2).isNull();
	}

}
