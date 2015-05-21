package cinema.dao;

import java.security.MessageDigest;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cinema.models.User;

@Singleton
public class UserDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addUser(User user){
		user.setPassword(getHashedPassword(user.getPassword()));
		em.persist(user);
	}
	
	public boolean validateUserCredentials(String userName, String password){
		String txtQuery = "SELECT u FROM User u WHERE u.userName=:userName AND u.password=:password";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return queryUser(query) != null;
	}
	
	private User queryUser(TypedQuery<User> query){
		try {
			return query.getSingleResult();
		} catch (Exception e){
			return null;
		}
	}
	
	private String getHashedPassword(String password){
		try {
			MessageDigest mda = MessageDigest.getInstance("SHA-512");
			password = new String(mda.digest(password.getBytes()));
		} catch (Exception e){
			e.printStackTrace();
		}
		return password;
	}

}
