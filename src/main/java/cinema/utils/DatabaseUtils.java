package cinema.utils;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cinema.dao.ProjectionDAO;
import cinema.dao.UserDAO;
import cinema.model.Projection;
import cinema.model.User;

@Stateless
public class DatabaseUtils {
    
    private static User[] USERS = {
            new User("First User", "123456", "first.user@somemail.com", "A", "AA", "088888888",
                    new Date()),
            new User("Second User", "Test1234", "second.user@somemail.com", "B", "BB", "099999999",
                    new Date()),
            new User("Third User", "98411TA", "third.user@somemail.com", "C", "CC", "00000000000",
                    new Date())};

    private static Projection[] PROJECTIONS = {
            new Projection("Film1", 12,12),
            new Projection("Film2", 124,215)};

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ProjectionDAO projectionDAO;
    
    @EJB
    private UserDAO userDAO;
    
    public void addTestDataToDB() {
        deleteData();
        addTestUsers();
        addTestProjections();
    }

    private void deleteData() {
        em.createQuery("DELETE FROM Projection").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
   }

    private void addTestUsers() {
        for (User user : USERS) {
            userDAO.addUser(user);
        }
    }

    private void addTestProjections() {
        for (Projection projection : PROJECTIONS) {
            projectionDAO.addProjection(projection);
        }
    }
}
