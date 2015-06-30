package cinema.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TICKETS")
@NamedQueries({
		@NamedQuery(name = "getAllTickets", query = "SELECT t FROM Ticket t"),
		@NamedQuery(name = "findTicketByProjection", query = "SELECT t FROM Ticket t WHERE t.projection = :projection") })
public class Ticket implements Serializable {

	private static final long serialVersionUID = -473800766770173540L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Timestamp lastAction;

	private Projection projection;

	private User owner;

	private int seat;

	private boolean isUserCheckedIn;

	public Ticket() {

	}

	public Ticket(Projection projection, User owner, int seat) {
		this.projection = projection;
		this.owner = owner;
		this.seat = seat;
		lastAction = new Timestamp(new Date().getTime());
		isUserCheckedIn = false;
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

	public boolean getIsUserCheckedIn() {
		return isUserCheckedIn;
	}

	public void setIsUserCheckedIn(boolean isUserCheckedIn) {
		this.isUserCheckedIn = isUserCheckedIn;
	}

	public Timestamp getTimestamp() {
		return lastAction;
	}

	public void setTimestamp(Date date) {
		lastAction = new Timestamp(date.getTime());
	}

	@Override
	public String toString() {
		return "{\"movieTitle\" : " + "\"" + projection.getMovieTitle() + "\"" + ", \"projectionId\" : " + "\"" + projection.getId() + "\"" + ", \"startTime\" : " + "\"" + projection.getStartTime() + "\""+ ", \"ownerEmail\" : " + "\"" + owner.getEmail() + "\""
				+ ", \"seat\" : " + "\"" + seat + "\""+ "}";
	}

}
