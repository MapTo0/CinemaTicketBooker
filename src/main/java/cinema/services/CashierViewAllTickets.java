package cinema.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import cinema.dao.UserDAO;
import cinema.model.Projection;
import cinema.model.User;

@Singleton
@Path("/viewtickets")
public class CashierViewAllTickets {

	
	@Inject
	private UserDAO userDAO;
	
	@GET
	@Path("{email}")
	@Produces("application/json")
	public Collection<Projection> getBoughtTickets(@PathParam("email") String email) {
		User user = userDAO.findUserByName(email);
		if(user != null){
			System.out.println("NOT NULL");
			if(user.getCurrentProjections() == null){
				System.out.println("Collection is null");
			} else {
				System.out.println("Collection is not null");
			}
			System.out.println(user.getCurrentProjections().size());
			return user.getCurrentProjections();
		} else {
			System.out.println("NULL");
		}
		return null;
	}
}
