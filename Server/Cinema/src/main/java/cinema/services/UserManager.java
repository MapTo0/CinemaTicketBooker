package cinema.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import cinema.dao.UserDAO;
import cinema.models.User;

@Stateless
@Path("user")
public class UserManager {
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private UserContext context;
	
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public void registerUser(User newUser){
		userDAO.addUser(newUser);
		context.setCurrentUser(newUser);
	}
}
