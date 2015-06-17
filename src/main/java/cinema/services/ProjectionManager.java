package cinema.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import cinema.dao.ProjectionDAO;
import cinema.model.Projection;

@Stateless
@Path("projection")
public class ProjectionManager {

	@Inject
	private ProjectionDAO projectionDAO;
	
	@Inject
	private UserContext userContext;

	@GET
	@Produces("application/json")
	public Collection<Projection> getAllProjections() {
		return projectionDAO.getAllProjections();
	}

	@GET
	@Path("{projectionId}")
	@Produces("application/json")
	public String places(@PathParam("projectionId") String projectionId) {
		return projectionDAO.findById(Long.parseLong(projectionId)).getPlaces().toString();
	}
	
	@PUT
	@Path("/buy")
	public Response buyTicket(@QueryParam("projectionId") String projectionId, @QueryParam("place") String place){
		Projection projection = projectionDAO.findById(Long.parseLong(projectionId));
		if(projection != null){
			projectionDAO.buyTicket(projection, userContext.getCurrentUser(), Integer.parseInt(place));
		}
		
		return Response.noContent().build();
	}
}
