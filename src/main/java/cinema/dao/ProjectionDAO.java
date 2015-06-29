package cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cinema.model.Projection;
import cinema.model.Ticket;

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
				"findByMovieTitle", Projection.class).setParameter(
				"movieTitle", movieTitle);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void buyTicket(Ticket ticket) {
		Projection foundProjection = findByMovieTitle(ticket.getProjection()
				.getMovieTitle());
		int freePlaces = foundProjection.getFreePlaces() - 1;
		foundProjection.getPlaces().add(ticket.getSeat(), false);
		foundProjection.setFreePlaces(freePlaces);
		ticket.getOwner().getCurrentTickets().add(ticket);
	}

}
