package cinema.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import cinema.dao.UserDAO;
import cinema.model.User;

@Stateless
@Path("user")
public class UserManager {

    @Inject
    private UserDAO userDAO;
    
    @Inject
    private UserContext context;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerUser(User newUser) {
        userDAO.addUser(newUser);
        context.setCurrentUser(newUser);
    }

}
