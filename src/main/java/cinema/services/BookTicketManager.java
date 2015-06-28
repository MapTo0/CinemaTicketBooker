package cinema.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import cinema.model.ProjectionBookings;

public class BookTicketManager {
	private static final Map<Long, ProjectionBookings> map = new HashMap<>();
	private static Object lock;

	@Inject
	private UserContext context;

	@POST
	@Path("/book")
	public Response bookTicket(
			@QueryParam(value = "projectionId") String projectionId,
			@QueryParam(value = "seat") String seat) {
		synchronized (lock) {
			if (map.containsKey(projectionId)) {
				ProjectionBookings projection = map.get(projectionId);

				if (projection.isTicketBooked(Integer.valueOf(seat))) {
					System.out.println("The seat has already been booked");
					return Response.status(401).build();
				} else {
					projection.bookTicket(Integer.valueOf(seat),
							context.getCurrentUser());
					return Response.status(200).build();
				}
			} else {
				System.out.println("No such projection exists");
				return Response.status(401).build();
			}
		}
	}
}
