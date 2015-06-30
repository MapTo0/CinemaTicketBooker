package cinema.services;

import java.net.HttpURLConnection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cinema.dao.UserDAO;
import cinema.model.User;

@Stateless
@Path("user")
public class UserManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private UserDAO userDAO;

	@Inject
	private UserContext context;

	@Path("register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(User newUser) {
		if(newUser.getFirstName().length() > 0 && newUser.getFirstName().length() < 20 && newUser.getLastName().length() > 0 && newUser.getLastName().length() < 20){
			if(newUser.getPassword().length() >= 8){
				if (userDAO.addUser(newUser)) {
					return RESPONSE_OK;
				}
				
			}
		}
		return Response.status(401).build();
	}

	@Path("login")
	@POST
	@Consumes("application/json")
	public Response loginUser(User user) {
		boolean isUserValid = userDAO.validateUserCredentials(user.getEmail(),
				user.getPassword());
		if (!isUserValid) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}

		User curUser = userDAO.findUserByName(user.getEmail());
		System.out.println(curUser);
		context.setCurrentUser(curUser);
		return RESPONSE_OK;
	}

	@Path("authenticaed")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response isAuthenticated() {
		if (context.getCurrentUser() == null) {
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).build();
		}
		return RESPONSE_OK;
	}

	@Path("current")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser() {
		if (context.getCurrentUser() == null) {
			System.out.println(context.getCurrentUser().getFirstName());
			return "null";
		}
		return context.getCurrentUser().toString();
	}

	@Path("logout")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public void logoutUser() {
		context.setCurrentUser(null);
	}
}
