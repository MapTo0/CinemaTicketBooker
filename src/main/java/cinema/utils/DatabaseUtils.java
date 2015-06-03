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
            new User("test1@mail.bg", "123", "A", "AA"),
            new User("test2@mail.bg", "1234", "B", "BB"),
            new User("test2@mail.bg", "1235", "V", "VV")};

    private static Projection[] PROJECTIONS = {
            new Projection("Film1", 12,12, "16:00"),
            new Projection("Film2", 124,215, "19:00")};

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
