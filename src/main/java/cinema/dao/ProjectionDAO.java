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

	public Projection findByAuthorAndTitle(String movieTitle) {
		TypedQuery<Projection> query = em.createNamedQuery(
				"findByAuthorAndTitle", Projection.class).setParameter(
				"movieTitle", movieTitle);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void buyTicket(Projection projection, User userWhoBuyTicket) {
		Projection foundProjection = findById(projection.getId());
		int freePlaces = foundProjection.getFreePlaces() - 1;
		foundProjection.setFreePlaces(freePlaces);
		userWhoBuyTicket.getCurrentProjections().add(foundProjection);
	}

}
