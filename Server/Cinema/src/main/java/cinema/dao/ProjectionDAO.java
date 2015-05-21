package cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cinema.models.Projection;
import cinema.models.User;

@Singleton
public class ProjectionDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addProjection(Projection projection){
		Projection foundProjection = findByMovieTitle(projection.getMovieTitle());
		if(foundProjection == null){
			em.persist(projection);
		} 
	}
	
	public Collection<Projection> getAllProjection(){
		return em.createNamedQuery("Select p From Projection p", Projection.class).getResultList();
	}
	
	public Projection findById(long key){
		return em.find(Projection.class, key);
	}
	
	public Projection findByMovieTitle(String movieTitle){
		String txtQuery = "SELECT p FROM Projection p WHERE p.movieTitle=:movieTitle";
		TypedQuery<Projection> query = em.createQuery(txtQuery, Projection.class);
		query.setParameter("movieTitle", movieTitle);
		try {
			return query.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
	
	public void buyTicket(Projection projection, User user){
		Projection foundProjection = findById(projection.getId());
		int newFreePlaces = foundProjection.getFreePlaces() - 1;
		foundProjection.setFreePlaces(newFreePlaces);
		user.getCurrentProjection().add(foundProjection);
	}

}
