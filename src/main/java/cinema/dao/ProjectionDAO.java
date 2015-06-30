package cinema.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Singleton;
import javax.inject.Inject;
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
	
	@Inject
	private TicketDAO ticketDAO;

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
		System.out.println(ticket.getOwner());
		System.out.println(ticket.getOwner().getCurrentTickets());
		Set<Ticket> curTickets = new HashSet<Ticket>();
		curTickets.add(ticket);
		System.out.println(curTickets);
		//ticket.getOwner().getCurrentTickets().add(ticket);
		//ticket.getOwner().setCurrentTickets(ticket.getOwner().getCurrentTickets().add(e));
		ticket.getOwner().setCurrentTickets(curTickets);
		
	}

}
