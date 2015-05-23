package cinema.utils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cinema.dao.BookDAO;
import cinema.library.Projection;

@Stateless
public class DatabaseUtils {
    
   /* private static User[] USERS = {
            new User("First User", "123456", "first.user@somemail.com", "Nqkoi1", "nqkoi1",
                    new Date()),
            new User("Second User", "Test1234", "second.user@somemail.com", "Nqkoi2", "nqkoi2",
                    new Date()),
            new User("Third User", "98411TA", "third.user@somemail.com", "Nqkoi3", "nqkoi3",
                    new Date())};*/

    private static Projection[] BOOKS = {
            new Projection("Film1", 12, 42, "16:00"),
            new Projection("Tom Sawyer", 15, 22, "16:26"),
            new Projection("Film3", 15, 251, "12:21")};

    @PersistenceContext
    private EntityManager em;

    @EJB
    private BookDAO bookDAO;
    
    //@EJB
  //  private UserDAO userDAO;
    
    public void addTestDataToDB() {
        deleteData();
     //   addTestUsers();
        addTestBooks();
    }

    private void deleteData() {
        em.createQuery("DELETE FROM Projection").executeUpdate();
      //  em.createQuery("DELETE FROM User").executeUpdate();
   }

 /*   private void addTestUsers() {
        for (User user : USERS) {
            userDAO.addUser(user);
        }
    }*/

    private void addTestBooks() {
        for (Projection book : BOOKS) {
            bookDAO.addBook(book);
        }
    }
}
