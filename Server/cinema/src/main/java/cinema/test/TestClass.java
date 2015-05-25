package cinema.test;

import java.util.Collection;

import javax.persistence.EntityManager;

import cinema.dao.ProjectionDAO;
import cinema.dao.UserDAO;
import cinema.model.Projection;
import cinema.model.User;
import cinema.utils.DatabaseUtils;

public class TestClass {
    private static UserDAO userDAO;
    
    private static ProjectionDAO projectionDAO;
   
    public static void main(String[] args) {
        DatabaseUtils dbUtils = new DatabaseUtils();
        dbUtils.addTestDataToDB();
        EntityManager em = dbUtils.initEntityManager();
        
        userDAO = new UserDAO(em);
        projectionDAO = new ProjectionDAO(em);
                      
        Collection<User> booksOfUser = userDAO.getAllUsers();
        Collection<Projection> projections = projectionDAO.getAllProjections();
        for(User user : booksOfUser){
        	System.out.println(user.toString());
        	
        }
        System.out.println(projections);
        dbUtils.closeEntityManager(em);
  
    }
    
}