package cinema.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cinema.dao.ProjectionDAO;
import cinema.dao.UserDAO;
import cinema.utils.DatabaseUtils;

@WebServlet(urlPatterns = "/TetServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private UserDAO userDAO;
	
	@EJB
	private ProjectionDAO projectionDAO;
	
	@EJB
	private DatabaseUtils databaseUtils;
	
	public void init() throws ServletException{
		databaseUtils.addTestDataToDB();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectionId = request.getParameter("projectionId");
		
		Gson gson = new Gson();
		String resource = null;
		
		if(projectionId == null){
			resource = gson.toJson(projectionDAO.getAllProjection());
		} else {
			resource = gson.toJson(projectionDAO.findById(Long.parseLong(projectionId)));
		}
		
		response.setContentType("application/json");
		response.getWriter().print(resource);
		response.getWriter().flush();
	}
}
