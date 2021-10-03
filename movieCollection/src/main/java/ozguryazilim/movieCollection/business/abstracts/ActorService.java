package ozguryazilim.movieCollection.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;

import ozguryazilim.movieCollection.entities.concretes.Actor;


public interface ActorService {
	List<Actor> getAll();

	Actor getById(int id);

	List<Actor> getByMovie_Id(int id);

	void Add(Actor actor);

	Actor getByActorId(int id);

	void deleteActorById(int id);

	List<Actor> getSearchActor(String search);

	Page<Actor> findPage(int pageNo, int pageSize, String sortField, String sortDirection);

}
