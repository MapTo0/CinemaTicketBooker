package cinema.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import cinema.dao.TicketDAO;
import cinema.dao.UserDAO;
import cinema.model.Ticket;
import cinema.model.User;

@Singleton
@Path("viewtickets")
public class CashierViewAllTickets {

	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private TicketDAO ticketDAO;
	
	@GET
	@Path("/user")
	@Produces("application/json")
	public String getBoughtTicketsByUser(@QueryParam("email") String email) {
		User user = userDAO.findUserByName(email);
		if(user != null){
			return user.getCurrentTickets().toString();
		}
		return null;
	}
	
	@GET
	@Path("/projection")
	public Collection<Ticket> getBoughtTicketsForProjection(@QueryParam("movieTitle") String movieTitle){
		return ticketDAO.findByMovieTitle(movieTitle);
	}
}
