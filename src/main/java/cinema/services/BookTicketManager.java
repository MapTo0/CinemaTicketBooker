package cinema.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cinema.dao.ProjectionDAO;
import cinema.model.Projection;
import cinema.model.ProjectionBookings;
import cinema.model.Ticket;

@Singleton
@Path("booking")
public class BookTicketManager {
	private static int RESERVE_TIME_IN_MILISECONDS = 1000 * 60 * 10;
	private static final int WAIT_TIME_NO_TICKETS = 1000 * 5;
	private static final Object lock = new Object();
	private static final Runnable thread = new Runnable() {
		@Override
		public synchronized void run() {
			while (true) {
				if (ticketExpirationTimes.isEmpty()) {
					try {
						wait(WAIT_TIME_NO_TICKETS); // waits until it is time
													// for next
						// ticket to be removed
					} catch (InterruptedException e) {
						java.lang.System.out
								.println("THERE HAS BEEN A PROBLEM");
					}
				} else {
					while (!ticketExpirationTimes.isEmpty()) {
						Date expiration = new Date(ticketExpirationTimes.peek()
								.getTimestamp().getTime()
								+ RESERVE_TIME_IN_MILISECONDS);
						if (expiration.before(new Date())) {
							Ticket ticket = ticketExpirationTimes.peek();
							map.get(ticket.getProjection().getId())
									.removeBooked(ticket.getSeat());
							ticketExpirationTimes.poll();
						} else {
							try {
								wait(expiration.getTime()
										- new Date().getTime() + 50);
							} catch (InterruptedException e) {
								java.lang.System.out.println("INTERRUPTED");
							}
						}

					}
				}
			}
		}
	};

	private static final Queue<Ticket> ticketExpirationTimes = new LinkedBlockingQueue<>();

	private static boolean isInitialized = false;
	private static Map<Long, ProjectionBookings> map = new HashMap<>();

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
			new Thread(thread).start();
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
						projection.removeBooked(addedSeats);
						return Response.status(401).build();
					} else {
						projection.bookTicket(Integer.valueOf(seat),
								context.getCurrentUser());
						addedSeats.add(Integer.valueOf(seat));
					}
				}
				for (Integer i : addedSeats) {
					ticketExpirationTimes.add(projection.getTicket(i));
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

	@GET
	@Path("/userBookedTickets")
	@Produces("application/json")
	public Collection<Ticket> getUserBookedTickets(
			@QueryParam(value = "email") String userEmail) {
		List<Ticket> userTickets = new ArrayList<>();
		for (Entry<Long, ProjectionBookings> entry : map.entrySet()) {
			userTickets
					.addAll(entry.getValue().getUserBookedTickets(userEmail));
		}

		return userTickets;
	}
}
