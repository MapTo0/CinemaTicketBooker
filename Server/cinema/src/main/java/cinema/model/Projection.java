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
@Table(name = "PROJECTIONS")
@NamedQueries({
		@NamedQuery(name = "getAllProjections", query = "SELECT p FROM Projection p"),
		@NamedQuery(name = "getProjectionByMovieTitle", query = "SELECT p FROM Projection p WHERE p.movieTitle=:movieTitle") })
public class Projection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5375291637351412248L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String movieTitle;

	private int hallNumber;

	private int freeSpaces;

	// Not String
	private String startHour;

	public Projection() {

	}

	public Projection(String movieTitle, int hallNumber, int freeSpaces,
			String startHour) {
		super();
		this.movieTitle = movieTitle;
		this.hallNumber = hallNumber;
		this.freeSpaces = freeSpaces;
		this.startHour = startHour;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHallNumber() {
		return hallNumber;
	}

	public void setHallNumber(int hallNumber) {
		this.hallNumber = hallNumber;
	}

	public int getFreeSpaces() {
		return freeSpaces;
	}

	public void setFreeSpaces(int freeSpaces) {
		this.freeSpaces = freeSpaces;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	@Override
	public String toString() {
		return "Projection [movieTitle=" + movieTitle + ", hallNumber="
				+ hallNumber + ", freeSpaces=" + freeSpaces + ", startHour="
				+ startHour + "]";
	}

}
