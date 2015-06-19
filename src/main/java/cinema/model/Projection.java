package cinema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "getAllProjections", query = "SELECT b FROM Projection b") })
public class Projection implements Serializable {

	private static final long serialVersionUID = -2929008106626811914L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String movieTitle;

	private int hallNumber;

	private int freePlaces;

	@ElementCollection
	private List<Boolean> places = new ArrayList<>();

	private List<Date> reserved = new ArrayList<>();

	// TODO change var type
	private String startTime;

	private String posterUrl;

	public Projection() {
	}

	public Projection(String movieTitle, int hallNumber, int freePlaces,
			String startTime, String posterUrl) {
		super();
		this.movieTitle = movieTitle;
		this.hallNumber = hallNumber;
		this.freePlaces = freePlaces;
		this.startTime = startTime;
		this.posterUrl = posterUrl;
		initPlaces(freePlaces);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public List<Boolean> getPlaces() {
		return this.places;
	}

	public List<Date> getReserved() {
		return reserved;
	}

	public void initPlaces(int freePlaces) {
		for (int i = 0; i < freePlaces; i++) {
			this.places.add(true);
			reserved.add(null);
		}
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";

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