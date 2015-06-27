package cinema.services;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Singleton
public class CashierViewAllTickets {

	@GET
	@Produces("application/json")
	public String getBoughtTickets(
			@QueryParam("projectionId") String projectionId) {

	}
}
