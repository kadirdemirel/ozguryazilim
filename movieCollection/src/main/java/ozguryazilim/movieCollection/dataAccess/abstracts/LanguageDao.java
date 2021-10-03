package ozguryazilim.movieCollection.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ozguryazilim.movieCollection.entities.concretes.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageDao extends JpaRepository<Language, Integer> {

	Optional<Language> findByLanguageName(String languageName);

	@Query(value = "SELECT * FROM languages l WHERE l.language_name LIKE %:keyword% ", nativeQuery = true)
	List<Language> findLanguagesByKeyword(@Param("keyword") String keyword);
}
