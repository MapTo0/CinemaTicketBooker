package cinema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "findByTitle", query = "SELECT b FROM Projection b WHERE b.movieTitle = :movieTitle"),
        @NamedQuery(name = "getAllProjections", query = "SELECT b FROM Projection b")})
public class Projection implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String movieTitle;

    private int hallNumber;
    
    private int freePlaces;
    
    private String hour;

    public Projection() {
    }

   

    public Projection(String movieTitle, int hallNumber, int freePlaces,
			String hour) {
		super();
		this.movieTitle = movieTitle;
		this.hallNumber = hallNumber;
		this.freePlaces = freePlaces;
		this.hour = hour;
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
            result += "title: " + movieTitle;
        if (hour != null && !hour.trim().isEmpty())
            result += ", author: " + hour;
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