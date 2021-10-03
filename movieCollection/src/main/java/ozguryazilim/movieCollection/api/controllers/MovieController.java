package ozguryazilim.movieCollection.api.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ozguryazilim.movieCollection.business.abstracts.ActorService;
import ozguryazilim.movieCollection.business.abstracts.MovieService;

import ozguryazilim.movieCollection.entities.concretes.Movie;

@Controller
public class MovieController {
	MovieService _movieService;
	ActorService _actorService;

	public MovieController(MovieService movieService, ActorService actorService) {
		_movieService = movieService;
		_actorService = actorService;

	}

	@GetMapping("/")
	public String viewHomePage(Model model) {

		return findPage(1, "movieName", "asc", model);

	}

	@GetMapping("/showNewMovieForm")
	public String showNewMovieForm(Model model) {
		Movie movie = new Movie();
		model.addAttribute("movie", movie);

		return "new_movie";
	}

	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie movie) {
		_movieService.add(movie);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
		Movie movie = _movieService.getMovieById(id);

		model.addAttribute("movie", movie);
		return "update_movie";
	}

	@GetMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable(value = "id") int id) {
		try {
			_movieService.deleteMovieById(id);
			return "redirect:/";
		} catch (Exception e) {
			return "error_page";
		}

	}

	@PostMapping("/showSearchResult")
	public String showSearchResult(@ModelAttribute("search") String search, Model model) {
		List<Movie> listMovie = _movieService.getSearchMovie(search);
		model.addAttribute("listMovies", listMovie);
		model.addAttribute("search", search);
		model.addAttribute("listMovieSize", listMovie.size());
		return findPage2(1, "movieName", "asc", search, model);
	}

	@GetMapping("/page/{pageNo}")
	public String findPage(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection, Model model) {

		int pageSize = 5;

		Page<Movie> page = _movieService.findPage(pageNo, pageSize, sortField, sortDirection);
		List<Movie> listMovies = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);

		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listMovies", listMovies);

		return "index";
	}

	@GetMapping("/pageNumber/{pageNo}")
	public String findPage2(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection, @ModelAttribute("search") String search, Model model) {

		int pageSize = 5;

		Page<Movie> page = _movieService.findPage(pageNo, pageSize, sortField, sortDirection);
		List<Movie> listMovies = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);

		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listMovies", listMovies);

		List<Movie> listMovie = _movieService.getSearchMovie(search);
		model.addAttribute("listMovies", listMovie);
		model.addAttribute("search", search);
		model.addAttribute("listMovieSize", listMovie.size());

		return "search_result";
	}

}
