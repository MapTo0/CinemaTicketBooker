package cinema.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import cinema.dao.ProjectionDAO;
import cinema.model.Projection;

import java.util.Collection;

@Stateless
@Path("projection")
public class ProjectionManager {

    @Inject
    private ProjectionDAO projectionDAO;

    @GET
    @Produces("application/json")
    public Collection<Projection> getAllProjections() {
    	return projectionDAO.getAllProjections();
    }

   

}
