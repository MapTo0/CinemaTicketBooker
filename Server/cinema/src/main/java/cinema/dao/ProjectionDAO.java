package cinema.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import cinema.model.Projection;
import cinema.model.User;

public class ProjectionDAO {
	
	private EntityManager em;
	
	public ProjectionDAO(EntityManager em){
		this.em = em;
	}
	
	public Collection<Projection> getAllProjections(){
		return em.createNamedQuery("getAllProjections", Projection.class).getResultList();
	}
	
	public void addProjection(Projection projection){
		try {
            em.getTransaction().begin();
            em.persist(projection);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
	}
	
	public Projection findProjectionByMovieTitle(String movieTitle){
		try {
			return em.createNamedQuery("getProjectionByMovieTitle", Projection.class)
				.setParameter("movieTitle", movieTitle).getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
	
	public void buyTicket(Projection projection, User user){
		Projection foundProjection = findProjectionByMovieTitle(projection.getMovieTitle());
		if(foundProjection != null){
			user.getCurrentProjections().add(projection);
			int newFreeSpaces = foundProjection.getFreeSpaces() - 1;
			foundProjection.setFreeSpaces(newFreeSpaces);
		}
	}

}
