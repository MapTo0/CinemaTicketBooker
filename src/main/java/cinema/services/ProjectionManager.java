package cinema.services;

import java.util.Collection;
import java.util.Date;

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
		return projectionDAO.findById(Long.parseLong(projectionId)).getPlaces()
				.toString();
	}

	public void borrowTicket(@QueryParam("projectionId") String projectionId,
			@QueryParam("place") String place) {
		Projection found = projectionDAO.findById(Long.parseLong(projectionId));
		if (found != null && userContext.getCurrentUser() != null) {
			int thePlace = Integer.parseInt(place);
			if (found.getFreePlaces() > 0
					&& found.getPlaces().get(thePlace) == true) {
				if (found.getReserved().get(thePlace) == null) {
					Date date = new Date();
					date.setMinutes(date.getMinutes() + 15);
					found.getReserved().set(thePlace, date);
				} else if (found.getReserved().get(thePlace).before(new Date())) {
					found.getReserved().set(thePlace, null);
					borrowTicket(projectionId, place);
				} else {
					System.out
							.println("there should be some error message here");
				}
			} else {
				System.out.println("there is a problem here");
			}
		}
	}

	@PUT
	@Path("/buy")
	public Response buyTicket(@QueryParam("projectionId") String projectionId,
			@QueryParam("place") String place) {
		Projection projection = projectionDAO.findById(Long
				.parseLong(projectionId));
		if (projection != null && userContext.getCurrentUser() != null) {
			projectionDAO.buyTicket(projection, userContext.getCurrentUser(),
					Integer.parseInt(place));
		}

		return Response.noContent().build();
	}
}
