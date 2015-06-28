package cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cinema.model.Projection;
import cinema.model.Ticket;

@Singleton
public class TicketDAO {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private ProjectionDAO projectionDAO;

	public void addTicket(Ticket ticket) {
		em.persist(ticket);
	}

	public Collection<Ticket> getAllTickets() {
		return em.createNamedQuery("getAllTickets", Ticket.class)
				.getResultList();
	}

	public Ticket findById(long key) {
		return em.find(Ticket.class, key);
	}

	public Collection<Ticket> findByMovieTitle(String movieTitle) {
		Projection projection = projectionDAO.findByMovieTitle(movieTitle);
		TypedQuery<Ticket> query = em.createNamedQuery(
				"findTicketByMovieTitle", Ticket.class).setParameter(
				"projection", projection);
		return query.getResultList();

	}

}
