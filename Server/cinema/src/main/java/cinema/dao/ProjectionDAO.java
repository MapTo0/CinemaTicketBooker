package cinema.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import cinema.model.Projection;
import cinema.model.User;

public class ProjectionDAO {
    private EntityManager em;

    public ProjectionDAO(EntityManager em) {
        this.em = em;
    }

    public void addBook(Projection book) {
        EntityTransaction tx = beginTransaction();
        Projection foundBook = findByTitle(book.getMovieTitle());
        if (foundBook == null) {
            em.persist(book);
        } 
        commitTransaction(tx);
    }

    public Collection<Projection> getAllProjections() {
        return em.createNamedQuery("getAllProjections", Projection.class).getResultList();
    }

    public Projection findById(long key) {
        return em.find(Projection.class, key);
    }

    public Projection findByTitle(String title) {
        TypedQuery<Projection> query = em
                .createNamedQuery("findByTitle", Projection.class)
                .setParameter("movieTitle", title);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void buyTicket(Projection projection, User userWhoBuyTicket) {
        EntityTransaction tx = beginTransaction();
        Projection foundProjection = findById(projection.getId());
        int newFreePlaces = foundProjection.getFreePlaces() - 1;
        foundProjection.setFreePlaces(newFreePlaces);
        userWhoBuyTicket.getCurrentProjections().add(foundProjection);
        commitTransaction(tx);
    }
    
    private EntityTransaction beginTransaction() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        return tx;
    }

    private void commitTransaction(EntityTransaction tx) {
        try {
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }
}
