package ozguryazilim.movieCollection.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import ozguryazilim.movieCollection.entities.concretes.Language;

public interface LanguageService {
	void Add(Language language);

	List<Language> getAll();

	Language getLanguageById(int id);

	void deleteLanguageById(int id);

	List<Language> getSearchLanguage(@Param("keyword") String keyword);

	Page<Language> findPage(int pageNo, int pageSize, String sortField, String sortDirection);
}
