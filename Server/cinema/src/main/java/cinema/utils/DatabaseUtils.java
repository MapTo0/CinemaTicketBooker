package cinema.utils;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cinema.dao.UserDAO;
import cinema.model.User;

public class DatabaseUtils {
    private static final String CINEMA_PERSISTENCE_UNIT = "cinema-persistence-unit";
    private static User[] USERS = {
            new User("First User", "123456", "first.user@somemail.com", "1", "11",
                    new Date()),
            new User("Second User", "Test1234", "second.user@somemail.com", "2", "22",
                    new Date()),
            new User("Third User", "98411TA", "third.user@somemail.com", "3", "33",
                    new Date())};

   

    private EntityManagerFactory emf;

    public DatabaseUtils() {
        emf = Persistence.createEntityManagerFactory(CINEMA_PERSISTENCE_UNIT);
    }

    public void addTestDataToDB() {
        EntityManager em = initEntityManager();
        addTestUsers(em);
        
        closeEntityManager(em);
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

    
}
