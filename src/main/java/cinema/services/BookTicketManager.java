package cinema.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import cinema.dao.ProjectionDAO;
import cinema.model.Projection;
import cinema.model.ProjectionBookings;

@Singleton
public class BookTicketManager {
	private static Map<Long, ProjectionBookings> map = new HashMap<>();
	private static boolean isInitialized = false;
	private static final Object lock = new Object();

	@Inject
	private UserContext context;

	@Inject
	private ProjectionDAO projectionDao;

	private Map<Long, ProjectionBookings> init() {
		Collection<Projection> projections = projectionDao.getAllProjections();
		Map<Long, ProjectionBookings> map = new HashMap<>();
		for (Projection projection : projections) {
			map.put(projection.getId(), new ProjectionBookings(projection));
		}
		return map;
	}

	@POST
	@Path("/book")
	public Response bookTicket(
			@QueryParam(value = "projectionId") String projectionIdString,
			@QueryParam(value = "seat") String selectedSeats) {
		if (!isInitialized) {
			map = init();
			isInitialized = true;
		}
		synchronized (lock) {
			Long projectionId = Long.valueOf(projectionIdString);
			String[] seatNumbers = selectedSeats.split(",");
			List<Integer> seats = new ArrayList<>();
			for (String seat : seatNumbers) {
				seats.add(Integer.valueOf(seat));
			}
			if (map.containsKey(projectionId)) {
				ProjectionBookings projection = map.get(projectionId);
				List<Integer> addedSeats = new ArrayList<>();
				for (Integer seat : seats) {
					if (projection.isTicketBooked(Integer.valueOf(seat))) {
						System.out.println("The seat has already been booked");
						projection.removeBooked(addedSeats);
						return Response.status(401).build();
					} else {
						projection.bookTicket(Integer.valueOf(seat),
								context.getCurrentUser());
						addedSeats.add(Integer.valueOf(seat));
					}
				}
				return Response.ok().build();
			} else {
				System.out.println("No such projection exists");
				return Response.status(401).build();
			}
		}
	}

	@POST
	@Path("/bookedTickets")
	public List<Integer> getBookedTickets(
			@QueryParam(value = "projectionId") String projectionId) {
		ProjectionBookings projection = map.get(Long.valueOf(projectionId));
		if (projection == null) {
			return null;
		} else {
			return projection.getBookedPlacesArray();
		}
	}
}
