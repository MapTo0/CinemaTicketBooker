package cinema.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@Table(name = "USERS")
@NamedQueries({
    
    @NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u")})
public class User implements Serializable {

    private static final long serialVersionUID = -7196507424378163030L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String email;

    private String password;

    private String firstName;
   
    private String lastName;
    
    @OneToMany
    private Set<Projection> currentProjections = new HashSet<>();

    public User() {
    }

    public User(String email, String password, String firstName, String lastName) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (email != null && !email.trim().isEmpty())
            result += "email: " + email;
        if (password != null && !password.trim().isEmpty())
            result += ", password: " + password;
        if (firstName != null && !firstName.trim().isEmpty())
            result += ", firstName: " + firstName;
        if (lastName != null && !lastName.trim().isEmpty())
        	result += ", lastName: " + lastName;
        return result;
    }

    public Set<Projection> getCurrentProjections() {
        return this.currentProjections;
    }

    public void setCurrentProjections(final Set<Projection> currentProjections) {
        this.currentProjections = currentProjections;
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