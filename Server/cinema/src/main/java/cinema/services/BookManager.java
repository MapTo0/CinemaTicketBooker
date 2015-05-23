package cinema.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cinema.dao.BookDAO;
import cinema.library.Projection;


@Stateless
@Path("projection")
public class BookManager {

    @Inject
    private BookDAO bookDAO;

  //  @Inject
    //private UserContext userContext;

    @GET
    @Produces("application/json")
    public Collection<Projection> getAllBooks() {
        return bookDAO.getAllBooks();
    }

   /* @GET
    @Path("{bookId}")
    @Produces("application/json")
    public Projection getBook(@PathParam("bookId") String bookId) {
        return bookDAO.findById(Long.parseLong(bookId));
    }

    @PUT
    @Path("/borrow")
    public Response borrowBook(@QueryParam("bookId") String bookId) {
        Projection bookToBorrow = bookDAO.findById(Long.parseLong(bookId));
        if (bookToBorrow != null) {
           // bookDAO.borrowBook(bookToBorrow, userContext.getCurrentUser());
        }
        return Response.noContent().build();
    }*/

}
