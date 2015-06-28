package cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cinema.model.Ticket;

@Singleton
public class TicketDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addTicket(Ticket ticket){
		em.persist(ticket);
	}
	
	public Collection<Ticket> getAllTickets(){
		return em.createNamedQuery("getAllTickets", Ticket.class)
				.getResultList();
	}
	
	public Ticket findById(long key) {
		return em.find(Ticket.class, key);
	}
	
	public Ticket findByMovieTitle(String movieTitle) {
		TypedQuery<Ticket> query = em.createNamedQuery(
				"findTicketByMovieTitle", Ticket.class).setParameter(
				"movieTitle", movieTitle);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}

