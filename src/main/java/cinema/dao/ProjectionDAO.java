package cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cinema.model.Projection;
import cinema.model.User;

@Singleton
public class ProjectionDAO {

	
	@PersistenceContext
	private EntityManager em;

	public void addProjection(Projection projection) {
		em.persist(projection);
	}

	public Collection<Projection> getAllProjections() {
		return em.createNamedQuery("getAllProjections", Projection.class)
				.getResultList();
	}

	public Projection findById(long key) {
		return em.find(Projection.class, key);
	}

	public Projection findByMovieTitle(String movieTitle) {
		TypedQuery<Projection> query = em.createNamedQuery(
				"findByAuthorAndTitle", Projection.class).setParameter(
				"movieTitle", movieTitle);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void buyTicket(Projection projection, User userWhoBuyTicket, String places) {
		String[] finalPlace = places.split(",");
		Projection foundProjection = findByMovieTitle(projection.getMovieTitle());
		int freePlaces = foundProjection.getFreePlaces() - finalPlace.length;
		for(String s : finalPlace) {
			foundProjection.getPlaces().add(Integer.parseInt(s), false);
		}
		
		foundProjection.setFreePlaces(freePlaces);
		userWhoBuyTicket.getCurrentProjections().add(foundProjection);
	}

}
