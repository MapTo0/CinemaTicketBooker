package cinema.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "USERS")
@NamedQueries({

		@NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u"),
		@NamedQuery(name = "findUserByEmail", query = "SELECT u FROM User u WHERE u.email=:email") })
public class User implements Serializable {

	private static final long serialVersionUID = -7196507424378163030L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;

	private String password;

	private String firstName;

	private String lastName;

	private boolean isCashier;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Ticket> currentTickets = new HashSet<Ticket>();

	public User() {
	}

	public User(String email, String password, String firstName,
			String lastName, boolean isCashier) {
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isCashier = isCashier;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Ticket> getCurrentTickets() {
		return this.currentTickets;
	}

	public void setCurrentTickets(final Set<Ticket> currentTickets) {
		this.currentTickets = currentTickets;
	}

	public boolean isCashier() {
		return isCashier;
	}

	public void setCashier(boolean isCashier) {
		this.isCashier = isCashier;
	}

	@Override
	public String toString() {
		String result = "";
		if (email != null && !email.trim().isEmpty())
			result += "email: " + email;
		if (firstName != null && !firstName.trim().isEmpty())
			result += ", firstName: " + firstName;
		result += ", isCashier: " + isCashier;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
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