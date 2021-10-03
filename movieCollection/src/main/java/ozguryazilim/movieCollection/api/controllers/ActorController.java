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

import ozguryazilim.movieCollection.business.abstracts.ActorService;
import ozguryazilim.movieCollection.business.abstracts.MovieService;
import ozguryazilim.movieCollection.entities.concretes.Actor;

import ozguryazilim.movieCollection.entities.concretes.Movie;

@Controller
public class ActorController {
	@Autowired
	ActorService _actorService;
	@Autowired
	MovieService _movieService;

	@GetMapping("/actors")
	public String viewHome(Model model) {
//		model.addAttribute("listActors", _actorService.getAll());
//		return "actor_list";

		return findPageActor(1, "name", "asc", model);

	}

	@GetMapping("/showNewActorForm")
	public String showNewActorForm(Model model, @ModelAttribute("movie") Movie movie) {
		Actor actor = new Actor();

		model.addAttribute("actor", actor);
		model.addAttribute("movie", _movieService.getAll());
		return "new_actor";
	}

	@PostMapping("/saveActor")
	public String saveActor(@ModelAttribute("actor") Actor actor) {

		_actorService.Add(actor);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdateActor/{id}")
	public String showFormForUpdateActor(@PathVariable(value = "id") int id, @ModelAttribute("movie") Movie movie,
			Model model) {
		Actor actor = _actorService.getByActorId(id);

		model.addAttribute("actor", actor);
		model.addAttribute("movie", _movieService.getAll());
		return "update_actor";
	}

	@GetMapping("/deleteActor/{id}")
	public String deleteActor(@PathVariable(value = "id") int id) {
		_actorService.deleteActorById(id);
		return "redirect:/actors";
	}

	@PostMapping("/showSearchActor")
	public String showSearchActor(@ModelAttribute("search") String search, Model model) {
		List<Actor> listActor = _actorService.getSearchActor(search);
		model.addAttribute("listActors", listActor);
		model.addAttribute("search", search);
		model.addAttribute("listActorSize", listActor.size());
	return findPageActor2(1, "name", "asc", search, model);
	}

	@GetMapping("/pageActor/{pageNo}")
	public String findPageActor(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection, Model model) {

		int pageSize = 5;

		Page<Actor> page = _actorService.findPage(pageNo, pageSize, sortField, sortDirection);
		List<Actor> listActors = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);

		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listActors", listActors);

		return "actor_list";
	}

	@GetMapping("/pageActorNumber/{pageNo}")
	public String findPageActor2(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection,
			@ModelAttribute("search") String search, Model model) {

		int pageSize = 5;

		Page<Actor> page = _actorService.findPage(pageNo, pageSize, sortField, sortDirection);
		List<Actor> listActors = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);

		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listActors", listActors);

		List<Actor> listActor = _actorService.getSearchActor(search);
		model.addAttribute("listActors", listActor);
		model.addAttribute("search", search);
		model.addAttribute("listActorSize", listActor.size());

		return "search_actor";
	}

}
