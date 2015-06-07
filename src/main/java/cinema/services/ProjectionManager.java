package cinema.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

	@Path("places")
	@GET
	@Produces("application/json")
	public String places(String movieTitle) {
		Collection<Projection> projections = projectionDAO.getAllProjections();
		for (Projection projection : projections) {
			if (projection.getMovieTitle().equals(movieTitle)) {
				return projection.getPlaces().toString();
			}
		}
		return null;
	}
}
