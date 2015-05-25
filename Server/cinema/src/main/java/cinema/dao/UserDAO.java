package cinema.dao;

import java.security.MessageDigest;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cinema.model.Projection;
import cinema.model.User;

public class UserDAO {

	private EntityManager em;

	public UserDAO(EntityManager em) {
		this.em = em;
	}

	public void addUser(User user) {
		try {
			em.getTransaction().begin();
			user.setPassword(getHashedPassword(user.getPassword()));
			em.persist(user);
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public boolean validateUserCredentials(String userName, String password) {
		String txtQuery = "SELECT u FROM User u WHERE u.userName=:userName AND u.password=:password";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("userName", userName);
		query.setParameter("password", getHashedPassword(password));
		return queryUser(query) != null;
	}

	public Collection<User> getAllUsers() {
		return em.createNamedQuery("getAllUsers", User.class).getResultList();
	}

	public User findUserByName(String userName) {
		String txtQuery = "SELECT u FROM User u WHERE u.userName = :userName";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("userName", userName);
		return queryUser(query);
	}

	public void buyTicket(String movieTitle, User user) {
		Projection foundProjection = em
				.createNamedQuery("getProjectionByMovieTitle", Projection.class)
				.setParameter("movieTitle", movieTitle).getSingleResult();
		if (foundProjection != null) {
			em.getTransaction().begin();
			user.addNewProjection(foundProjection);
			int newFreeSpaces = foundProjection.getFreeSpaces() - 1;
			foundProjection.setFreeSpaces(newFreeSpaces);
			em.getTransaction().commit();
		}
	}

	private User queryUser(TypedQuery<User> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	private String getHashedPassword(String password) {
		try {
			MessageDigest mda = MessageDigest.getInstance("SHA-512");
			password = new String(mda.digest(password.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
}