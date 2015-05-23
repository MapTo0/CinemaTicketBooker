package cinema.test;

import java.util.Collection;

import javax.persistence.EntityManager;

import cinema.dao.UserDAO;
import cinema.model.User;
import cinema.utils.DatabaseUtils;

public class TestClass {
    private static UserDAO userDAO;
   
    public static void main(String[] args) {
        DatabaseUtils dbUtils = new DatabaseUtils();
        dbUtils.addTestDataToDB();
        EntityManager em = dbUtils.initEntityManager();
        userDAO = new UserDAO(em);
        
       
        Collection<User> booksOfUser = userDAO.getAllBooks();
        System.out.println(booksOfUser);
        dbUtils.closeEntityManager(em);
  
    }
    
}
