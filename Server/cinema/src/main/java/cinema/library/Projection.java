package cinema.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "findByTitle", query = "SELECT p FROM Projection p WHERE p.title= :title"),
        @NamedQuery(name = "getAllProjections", query = "SELECT p FROM Projection p")})
public class Projection implements Serializable {

    private static final long serialVersionUID = -2929008106626811914L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private int hallNumber;

    private int freePlaces;

    private String hour;

    public Projection() {
    }

    public Projection(String title, int hallNumber, int freePlaces, String hour) {
        super();
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (title != null && !title.trim().isEmpty())
            result += "title: " + title;
       
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