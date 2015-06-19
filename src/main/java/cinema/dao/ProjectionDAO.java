package cinema.dao;

import java.util.Collection;
import java.util.Date;

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

	public void borrowTicket(Projection projection, User user, int place) {
		Projection found = findById(projection.getId());
		if (found != null && user != null) {
			if (found.getFreePlaces() > 0
					&& found.getPlaces().get(place) == true) {
				if (found.getReserved().get(place) == null) {
					Date date = new Date();
					date.setMinutes(date.getMinutes() + 15);
					found.getReserved().set(place, date);
				} else if (found.getReserved().get(place).before(new Date())) {
					found.getReserved().set(place, null);
					borrowTicket(projection, user, place);
				} else {
					System.out
							.println("there should be some error message here");
				}
			} else {
				System.out.println("there is a problem here");
			}
		}
	}

	public void buyTicket(Projection projection, User userWhoBuyTicket,
			int place) {
		Projection foundProjection = findById(projection.getId());
		int freePlaces = foundProjection.getFreePlaces() - 1;
		foundProjection.getPlaces().set(place, false);
		foundProjection.setFreePlaces(freePlaces);
		userWhoBuyTicket.getCurrentProjections().add(foundProjection);
	}

}
