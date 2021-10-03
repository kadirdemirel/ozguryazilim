package ozguryazilim.movieCollection.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ozguryazilim.movieCollection.business.abstracts.ActorService;
import ozguryazilim.movieCollection.dataAccess.abstracts.ActorDao;
import ozguryazilim.movieCollection.entities.concretes.Actor;

@Service
public class ActorManager implements ActorService {

	@Autowired
	ActorDao _actorDao;

	@Override
	public List<Actor> getAll() {
		return _actorDao.findAll();
	}

	@Override
	public Actor getById(int id) {
		return _actorDao.getById(id);
	}

	@Override
	public List<Actor> getByMovie_Id(int id) {
		return _actorDao.getByMovie_Id(id);
	}

	@Override
	public void Add(Actor actor) {
		_actorDao.save(actor);

	}

	@Override
	public Actor getByActorId(int id) {
		Optional<Actor> optional = _actorDao.findById(id);
		Actor actor = null;
		if (optional.isPresent()) {
			actor = optional.get();
		} else {
			throw new RuntimeException("Actor not found for id ::" + id);
		}
		return actor;
	}

	@Override
	public void deleteActorById(int id) {
		_actorDao.deleteById(id);

	}

	@Override
	public List<Actor> getSearchActor(String search) {
		List<Actor> listActors = _actorDao.findActorsByKeyword(search);
		return listActors;
	}

	@Override
	public Page<Actor> findPage(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return _actorDao.findAll(pageable);
	}

}
