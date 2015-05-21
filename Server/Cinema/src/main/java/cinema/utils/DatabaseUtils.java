package cinema.utils;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cinema.dao.ProjectionDAO;
import cinema.dao.UserDAO;
import cinema.models.Projection;
import cinema.models.User;

@Stateless
@Default
public class DatabaseUtils {
	
	private static User[] Users = {
		new User("test1", "1234", "test1@mail.com","FirstNameTest1", "LastNameTest1", new Date()),
		new User("test2", "1234", "test2@mail.com","FirstNameTest2", "LastNameTest2", new Date()),
		new User("test3", "1234", "test3@mail.com","FirstNameTest3", "LastNameTest3", new Date()),
		new User("test4", "1234", "test4@mail.com","FirstNameTest4", "LastNameTest4", new Date())
	};
	
	private static Projection[] Projections = {
		new Projection("MovieTitle1", 11, 50, "15:00"),
		new Projection("MovieTitle2", 12, 51, "17:00"),
		new Projection("MovieTitle3", 13, 52, "21:00"),
		new Projection("MovieTitle4", 14, 53, "23:00")
	};
	
	@PersistenceContext
	private EntityManager em;
		
	@EJB
	private UserDAO userDAO;
	
	@EJB
	private ProjectionDAO projectionDAO;
	
	public void addTestDataToDB(){
		deleteData();
		addTestUsers();
		addTestProjections();
	}
	
	private void deleteData(){
		em.createQuery("DELETE FROM User").executeUpdate();
		em.createQuery("DELETE FROM Projection").executeUpdate();
	}
	
	private void addTestUsers(){
		for(User user : Users){
			userDAO.addUser(user);
		}
	}
	
	private void addTestProjections(){
		for(Projection projection : Projections){
			projectionDAO.addProjection(projection);
		}
	}

}
