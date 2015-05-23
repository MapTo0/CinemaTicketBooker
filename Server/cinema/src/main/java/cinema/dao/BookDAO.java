package cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cinema.library.Projection;

@Singleton
public class BookDAO {

    @PersistenceContext
    private EntityManager em;

    public void addBook(Projection book) {
        Projection foundBook = findByTitle(book.getTitle());
        if (foundBook == null) {
            em.persist(book);
        } 
    }

    public Collection<Projection> getAllBooks() {
        return em.createNamedQuery("getAllProjections", Projection.class).getResultList();
    }

    public Projection findById(long key) {
        return em.find(Projection.class, key);
    }

    public Projection findByTitle(String title) {
        TypedQuery<Projection> query = em
                .createNamedQuery("findByTitle", Projection.class)
                .setParameter("title", title);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

   /* public void borrowBook(Projection bookToBorrow, User userWhoTakesTheBook) {
        Projection foundBook = findById(bookToBorrow.getId());
        int newAmount = foundBook.getAmount() - 1;
        foundBook.setAmount(newAmount);
        userWhoTakesTheBook.getCurrentBooks().add(foundBook);
    }

    public void returnBook(Projection book, User user) {
        Projection foundBook = findById(book.getId());
        int newAmount = book.getAmount() + 1;
        foundBook.setAmount(newAmount);
        User userWhoReturnsTheBook = em.find(User.class, user.getId());
        userWhoReturnsTheBook.getCurrentBooks().remove(foundBook);
    }
*/
}
