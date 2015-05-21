package cinema.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USERS")
public class User implements Serializable{

	private static final long serialVersionUID = 3688210157264723306L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String userName;
	
	private String password;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@OneToMany
	private Set<Projection> currentProjection = new HashSet<Projection>();

	public User(String userName, String password, String email,	String firstName, String lastName, Date dateOfBirth) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Set<Projection> getCurrentProjection(){
		return this.currentProjection;
	}
	
	public void setCurrentProjection(final Set<Projection> currentProjection){
		this.currentProjection = currentProjection;
	}
	
	@Override
	public String toString(){
		String result = getClass().getSimpleName() + " ";
		if(userName != null && !userName.trim().isEmpty())
			result += "userName: " + userName;
		if(password != null && !password.trim().isEmpty())
			result += ", password: " + password;
		if(email != null && !email.trim().isEmpty())
			result += ", email: " + email;
		if(firstName != null && !firstName.trim().isEmpty())
			result += ", firstName: " + firstName;
		if(lastName != null && !lastName.trim().isEmpty())
			result += ", lastName: " + lastName;
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(!(obj instanceof User)){
			return false;
		}
		
		User other = (User) obj;
		if(id != null){
			if(!id.equals(other.id)){
				return false;
			}
		}
		return true;
	}
	
}
