package cinema.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ProjectionBookings {
	private final Long id;
	private final Map<Integer, Ticket> bookedTickets;

	public ProjectionBookings(Long id) {
		bookedTickets = new HashMap<>();
		this.id = id;
	}

	public void addBookedTicket(User user, int place) {
		bookedTickets.put(place, new Ticket(this.id, user, place));
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

	public boolean isTicketBooked(int seat) {
		return bookedTickets.containsKey(seat);
	}

	public void bookTicket(int seat, User user) {
		Ticket ticket = new Ticket(id, user, seat);
		bookedTickets.put(seat, ticket);
	}
}
