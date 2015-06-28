package cinema.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TICKETS")
@NamedQueries({@NamedQuery(name = "getAllTickets", query = "SELECT t FROM Ticket t"), 
	@NamedQuery(name = "findTicketByMovieTitle", query = "SELECT t FROM Ticket t WHERE t.projection.movieTitle = :movieTitle")})
public class Ticket implements Serializable {
	
	private static final long serialVersionUID = -473800766770173540L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Projection projection;
	
	private User owner;
	
	private int seat;
	
	public Ticket() {

	}

	public Ticket(Projection projection , User owner, int seat) {
		this.projection = projection;
		this.owner = owner;
		this.seat = seat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Ticket [projection=" + projection + ", owner=" + owner
				+ ", seat=" + seat + "]";
	}
	
	
}
