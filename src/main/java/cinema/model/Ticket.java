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
@NamedQueries({ @NamedQuery(name = "getProjectionTickets", query = "SELECT t FROM Ticket t WHERE t.projectionId=:projectionId") })
public class Ticket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -473800766770173540L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(Long projectionId) {
		this.projectionId = projectionId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Long projectionId;
	private User owner;
	private int seat;

	public Ticket() {

	}
}
