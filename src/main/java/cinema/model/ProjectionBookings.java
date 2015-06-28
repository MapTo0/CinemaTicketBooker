package cinema.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ProjectionBookings {
	private final Map<Integer, Ticket> bookedTickets;
	private final Projection projection;

	public ProjectionBookings(Projection projection) {
		bookedTickets = new HashMap<>();
		this.projection = projection;
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
			System.out.println(entry.getKey().intValue());
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
}
