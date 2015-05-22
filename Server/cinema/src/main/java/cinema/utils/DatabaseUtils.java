package cinema.utils;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import cinema.dao.ProjectionDAO;
import cinema.dao.UserDAO;
import cinema.model.Projection;
import cinema.model.User;

public class DatabaseUtils {

    private static User[] USERS = {
            new User("First User", "123456", "first.user@somemail.com" , "a" , "b",
                    new Date()),
            new User("Second User", "Test1234", "second.user@somemail.com" , "c", " d",
                    new Date()),
            new User("Third User", "98411TA", "third.user@somemail.com", "e" , " f",
                    new Date())};

    private static Projection[] PROJECTIONS = {
            new Projection("Title1", 30,
                    40, "16:00"),
            new Projection("Title2", 50, 70, "18:00")};

    private EntityManagerFactory emf;

    public DatabaseUtils(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void addTestDataToDB() {
        EntityManager em = initEntityManager();
        deleteData(em);
        addTestUsers(em);
        addTestBooks(em);
        closeEntityManager(em);
    }

    private void deleteData(EntityManager em) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Projection").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
        em.getTransaction().commit();
   }

    public EntityManager initEntityManager() {
        return emf.createEntityManager();
    }

    public void closeEntityManager(EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }

    private void addTestUsers(EntityManager em) {
        UserDAO userDAO = new UserDAO(em);
        for (User user : USERS) {
            userDAO.addUser(user);
        }
    }

    private void addTestBooks(EntityManager em) {
        ProjectionDAO projectionDAO = new ProjectionDAO(em);
        for (Projection projection : PROJECTIONS) {
            projectionDAO.addBook(projection);
        }
    }
}
