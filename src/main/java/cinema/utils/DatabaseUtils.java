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
            new Projection("Furious Seven", 12,12, "16:00", "http://blogs-images.forbes.com/markhughes/files/2015/04/Furious-7.jpg"),
            new Projection("The Theory of Everything", 124,215, "19:00", "http://upload.wikimedia.org/wikipedia/en/b/b8/Theory_of_Everything.jpg"),
            new Projection("The Imitation Game", 13, 52, "21:00", "https://s-media-cache-ak0.pinimg.com/736x/3f/94/fb/3f94fb0e4327f51e73f0ed293be7ae86.jpg"),
            new Projection("Taken 3", 12,52, "11:00", "http://www.moviehdwallpapers.com/wp-content/uploads/2014/12/Taken-3-2015-movie-wallpaper.jpg"),
            new Projection("American Sniper", 52, 51, "21:41", "http://www.theamericanmirror.com/wp-content/uploads/2015/04/American-Sniper-Movie-Poster-7.jpg")};

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
