package cinema.dao;

import java.security.MessageDigest;
import java.util.Collection;

import javax.ejb.Singleton;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cinema.model.User;

@Singleton
public class UserDAO {

	@PersistenceContext
	private EntityManager em;

	public boolean addUser(User user) {
		String userEmail = user.getEmail();
		boolean isOk = false;
		try {
		em.createNamedQuery("findUserByEmail", User.class)
				.setParameter("email", userEmail).getSingleResult();
		} catch (NoResultException ex){
			user.setPassword(getHashedPassword(user.getPassword()));
			em.persist(user);
			isOk = true;
		}
		
		return isOk;
	}
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}

	public boolean validateUserCredentials(String email, String password) {
		String txtQuery = "SELECT u FROM User u WHERE u.email=:email AND u.password=:password";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("email", email);
		query.setParameter("password", getHashedPassword(password));
		return queryUser(query) != null;
	}

	public User findUserByName(String email) {
		String txtQuery = "SELECT u FROM User u WHERE u.email = :email";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("email", email);
		return queryUser(query);
	}

	public Collection<User> getAllUsers() {
		return em.createNamedQuery("getAllUsers", User.class).getResultList();
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