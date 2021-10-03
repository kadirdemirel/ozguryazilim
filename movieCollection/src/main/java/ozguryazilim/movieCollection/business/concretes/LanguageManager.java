package ozguryazilim.movieCollection.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ozguryazilim.movieCollection.business.abstracts.LanguageService;
import ozguryazilim.movieCollection.dataAccess.abstracts.LanguageDao;
import ozguryazilim.movieCollection.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {
	@Autowired
	LanguageDao _languageDao;

	@Override
	public void Add(Language language) {
		_languageDao.save(language);

	}

	@Override
	public List<Language> getAll() {
		return _languageDao.findAll();
	}

	@Override
	public Language getLanguageById(int id) {
		Optional<Language> optional = _languageDao.findById(id);
		Language language = null;
		if (optional.isPresent()) {
			language = optional.get();
		} else {
			throw new RuntimeException("Language not found for id ::" + id);
		}
		return language;
	}

	@Override
	public void deleteLanguageById(int id) {
		_languageDao.deleteById(id);

	}

	@Override
	public List<Language> getSearchLanguage(String keyword) {
		List<Language> searchLanguage = _languageDao.findLanguagesByKeyword(keyword);
		return searchLanguage;
	}

	@Override
	public Page<Language> findPage(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return _languageDao.findAll(pageable);
	}
}
