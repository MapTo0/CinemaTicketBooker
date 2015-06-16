package cinema.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import cinema.dao.ProjectionDAO;
import cinema.model.Projection;

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

	@GET
	@Path("{projectionId}")
	@Produces("application/json")
	public String places(@PathParam("projectionId") String projectionId) {
		Collection<Projection> projections = projectionDAO.getAllProjections();
		System.out.println(projectionId);
		for (Projection projection : projections) {
			if (projection.getId().equals(Long.parseLong(projectionId))) {
				return projection.getPlaces().toString();
			}
		}
		return null;
	}
}
