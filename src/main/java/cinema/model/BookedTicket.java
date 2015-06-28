package cinema.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookedTicket implements Serializable {

	private static final long serialVersionUID = -3764550360380396095L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Projection projection;

	private User user;

	private int seat;

	private Timestamp bookedTime;

	public BookedTicket(Projection projection, User user, int seat,
			Timestamp bookedTime) {
		super();
		this.projection = projection;
		this.user = user;
		this.seat = seat;
		this.bookedTime = bookedTime;
	}

	public BookedTicket() {

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public Timestamp getBookedTime() {
		return bookedTime;
	}

	public void setBookedTime(Timestamp bookedTime) {
		this.bookedTime = bookedTime;
	}

	@Override
	public String toString() {
		return "BookedTicket [projection=" + projection + ", user=" + user
				+ ", seat=" + seat + ", bookedTime=" + bookedTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookedTime == null) ? 0 : bookedTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((projection == null) ? 0 : projection.hashCode());
		result = prime * result + seat;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookedTicket other = (BookedTicket) obj;
		if (bookedTime == null) {
			if (other.bookedTime != null)
				return false;
		} else if (!bookedTime.equals(other.bookedTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (projection == null) {
			if (other.projection != null)
				return false;
		} else if (!projection.equals(other.projection))
			return false;
		if (seat != other.seat)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
