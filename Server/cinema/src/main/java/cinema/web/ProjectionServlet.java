package cinema.web;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.dao.ProjectionDAO;
import cinema.utils.DatabaseUtils;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/ProjectionServlet")
public class ProjectionServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -938090797779250333L;
	@PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        DatabaseUtils utils = new DatabaseUtils(emf);
        utils.addTestDataToDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectionId = req.getParameter("projectionId");

        ProjectionDAO projectionDAO = new ProjectionDAO(emf.createEntityManager());
        Gson gson = new Gson();
        String resource = null;

        if (projectionId == null) {
            resource = gson.toJson(projectionDAO.getAllProjections());
        } else {
            resource = gson.toJson(projectionDAO.findById(Long.parseLong(projectionId)));
        }

        resp.setContentType("application/json");
        resp.getWriter().print(resource);
        resp.getWriter().flush();
    }


}
