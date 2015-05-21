package cinema.services;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import cinema.models.User;

@SessionScoped
public class UserContext implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -735076540954674631L;
	
	private User currentUser;
	
	public User getCurrentUser(){
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser){
		this.currentUser = currentUser;
	}
	
	

}
