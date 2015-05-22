package cinema.web;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.dao.UserDAO;
import cinema.utils.DatabaseUtils;

import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        DatabaseUtils utils = new DatabaseUtils(emf);
        utils.addTestDataToDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     
    	UserDAO userDAO = new UserDAO(emf.createEntityManager());
        Gson gson = new Gson();
        String resource = null;

       
       resource = gson.toJson(userDAO.getAllUsers());
        

        resp.setContentType("application/json");
        resp.getWriter().print(resource);
        resp.getWriter().flush();
    }

}
