package cinema.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Projection implements Serializable {

	private static final long serialVersionUID = -5708354321390420991L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String movieTitle;

	private int hallNumber;

	private int freePlaces;

	// not string
	private String hour;

	public Projection(String movieTitle, int hallNumber, int freePlaces, String hour) {
		super();
		this.movieTitle = movieTitle;
		this.hallNumber = hallNumber;
		this.freePlaces = freePlaces;
		this.hour = hour;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public int getHallNumber() {
		return hallNumber;
	}

	public void setHallNumber(int hallNumber) {
		this.hallNumber = hallNumber;
	}

	public int getFreePlaces() {
		return freePlaces;
	}

	public void setFreePlaces(int freePlaces) {
		this.freePlaces = freePlaces;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (movieTitle != null && !movieTitle.trim().isEmpty())
			result += "movieTitle: " + movieTitle;
		if (hallNumber >= 0)
			result += ", hallNumber: " + hallNumber;
		if (freePlaces >= 0)
			result += ", freePlaces: " + freePlaces;
		if (hour != null && !hour.trim().isEmpty())
			;
		result += ", hour: " + hour;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Projection)) {
			return false;
		}
		Projection other = (Projection) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}
