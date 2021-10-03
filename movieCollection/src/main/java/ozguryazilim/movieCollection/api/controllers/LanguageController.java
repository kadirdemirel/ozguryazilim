package ozguryazilim.movieCollection.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ozguryazilim.movieCollection.business.abstracts.LanguageService;
import ozguryazilim.movieCollection.business.abstracts.MovieService;

import ozguryazilim.movieCollection.entities.concretes.Language;
import ozguryazilim.movieCollection.entities.concretes.Movie;

@Controller
public class LanguageController {
	@Autowired
	LanguageService _languageService;
	@Autowired
	MovieService _movieService;

	@GetMapping("/languages")
	public String viewHomeLanguage(Model model) {
//		model.addAttribute("listLanguages", _languageService.getAll());
//		return "language_list";

		return findPageLanguage(1, "languageName", "asc", model);

	}

	@GetMapping("/showNewLanguageForm")
	public String showNewLanguageForm(Model model, @ModelAttribute("movie") Movie movie) {
		Language language = new Language();

		model.addAttribute("language", language);
		model.addAttribute("movie", _movieService.getAll());
		return "new_language";
	}

	@PostMapping("/saveLanguage")
	public String saveLanguage(@ModelAttribute("language") Language language) {

		_languageService.Add(language);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdateLanguage/{id}")
	public String showFormForUpdateLanguage(@PathVariable(value = "id") int id, @ModelAttribute("movie") Movie movie,
			Model model) {
		Language language = _languageService.getLanguageById(id);

		model.addAttribute("language", language);
		model.addAttribute("movie", _movieService.getAll());
		return "update_language";
	}

	@GetMapping("/deleteLanguage/{id}")
	public String deleteLanguage(@PathVariable(value = "id") int id) {
		_languageService.deleteLanguageById(id);
		return "redirect:/languages";
	}

	@PostMapping("/showSearchLanguage")
	public String showSearchLanguage(@ModelAttribute("search") String search, Model model) {
		List<Language> listLanguage = _languageService.getSearchLanguage(search);
		model.addAttribute("listLanguages", listLanguage);
		model.addAttribute("search", search);
		model.addAttribute("listLanguageSize", listLanguage.size());
		return findPageLanguage2(1, "languageName", "asc", search, model);
	}

	@GetMapping("/pageLanguage/{pageNo}")
	public String findPageLanguage(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection,
			Model model) {

		int pageSize = 5;

		Page<Language> page = _languageService.findPage(pageNo, pageSize, sortField, sortDirection);
		List<Language> listLanguages = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);

		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listLanguages", listLanguages);

		return "language_list";
	}

	@GetMapping("/pageLanguageNumber/{pageNo}")
	public String findPageLanguage2(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection,
			@ModelAttribute("search") String search, Model model) {

		int pageSize = 5;

		Page<Language> page = _languageService.findPage(pageNo, pageSize, sortField, sortDirection);
		List<Language> listLanguages = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);

		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listLanguages", listLanguages);

		List<Language> listLanguage = _languageService.getSearchLanguage(search);
		model.addAttribute("listLanguages", listLanguage);
		model.addAttribute("search", search);
		model.addAttribute("listLanguageSize", listLanguage.size());

		return "search_language";
	}
}
