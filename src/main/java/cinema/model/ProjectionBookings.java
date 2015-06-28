package cinema.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ProjectionBookings {
	// default is 10 minutes
	private static int RESERVE_TIME_IN_MILISECONDS = 1000 * 60 * 10;

	private final Map<Integer, Ticket> bookedTickets;
	private final Projection projection;

	public ProjectionBookings(Projection projection) {
		bookedTickets = new HashMap<>();
		this.projection = projection;
	}

	public static void setReserveTime(int minutes) {
		RESERVE_TIME_IN_MILISECONDS = minutes * 60 * 1000;
	}

	public void removeExpiredBookings() {
		for (Entry<Integer, Ticket> entry : bookedTickets.entrySet()) {
			Date expireDate = new Date(entry.getValue().getTimestamp()
					.getTime()
					+ RESERVE_TIME_IN_MILISECONDS);
			if (new Date().after(expireDate)) {
				bookedTickets.remove(entry.getKey());
			}
		}
	}

	public List<Integer> getBookedPlacesArray() {
		List<Integer> bookedPlaces = new ArrayList<>();
		for (Entry<Integer, Ticket> entry : bookedTickets.entrySet()) {
			bookedPlaces.add(entry.getKey());
		}

		return bookedPlaces;
	}

	public boolean checkUserBookedTicket(int seat, User user) {
		Ticket ticket = bookedTickets.get(seat);

		if (ticket == null) {
			return false;
		} else if (ticket.getOwner().equals(user)) {
			return true;
		}

		return false;
	}

	public boolean isTicketBooked(Integer seat) {
		return bookedTickets.containsKey(seat);
	}

	public void bookTicket(Integer seat, User user) {
		Ticket ticket = new Ticket(this.projection, user, seat);
		bookedTickets.put(seat, ticket);
		for (Entry<Integer, Ticket> entry : bookedTickets.entrySet()) {
			System.out.println("The ticket is going to expire at "
					+ new Date(entry.getValue().getTimestamp().getTime()
							+ RESERVE_TIME_IN_MILISECONDS));
		}
	}

	public void removeBooked(List<Integer> seats) {
		for (int i : seats) {
			removeBooked(i);
		}
	}

	public void removeBooked(int seat) {
		if (bookedTickets.containsKey(seat)) {
			bookedTickets.remove(seat);
		}
	}

	public List<Ticket> getUserBookedTickets(String userEmail) {
		List<Ticket> userTickets = new ArrayList<>();
		for (Entry<Integer, Ticket> entry : bookedTickets.entrySet()) {
			String mail = entry.getValue().getOwner().getEmail();
			if (userEmail.equals(mail)) {
				userTickets.add(entry.getValue());
			}
		}

		return userTickets;
	}
}
