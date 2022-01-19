package com.uaic.Lab9.client;

import com.uaic.Lab9.dtos.CreateBookDto;
import com.uaic.Lab9.dtos.UpdateBookDto;
import com.uaic.Lab9.entities.Book;
import com.uaic.Lab9.services.BookService;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@ApplicationScoped
@Path("/v1/books")
public class BookController {
    @Inject
    private BookService bookService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(
            value = List.class,
            responseDescription = "Uploaded Books",
            responseCode = "200"
    )
    @APIResponse(
            responseCode = "404",
            description = "Author not found"
    )
    @APIResponse(
            responseCode = "500",
            description = "Server error"
    )
    @Operation(
            summary = "Get all books"
    )
    public Response getAll(
            @Parameter(
                    description = "The id of the author to filter documents by",
                    schema = @Schema(type = SchemaType.NUMBER)
            )
            @QueryParam("authorId") Integer authorId
    ) {
        try {
            List<Book> books = this.bookService.getAll(authorId);
            return Response.ok().entity(books).build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Create a book"
    )
    @Transactional
    public Response create(CreateBookDto createBookDto, @Context UriInfo uriInfo) {
        try {
            Book book = this.bookService.create(createBookDto);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(book.getId()));
            return Response.created(uriBuilder.build()).build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    @POST
    @Path("/{bookId}/authors/{libraryId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Add a book to the library"
    )
    @Transactional
    public Response addToLibrary(@PathParam("bookId") Integer bookId, @PathParam("libraryId") Integer libraryId) {
        try {
            this.bookService.addBookToLibrary(bookId, libraryId);
            return Response.noContent().build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Update a book"
    )
    @Counted
    @Transactional
    public Response update(@PathParam("id") Integer id, UpdateBookDto updateBookDto) {
        try {
            this.bookService.update(id, updateBookDto);
            return Response.noContent().build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Delete a book"
    )
    @Transactional
    public Response remove(@PathParam("id") Integer id) {
        try {
            this.bookService.remove(id);
            return Response.noContent().build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(("/{libraryId}"))
    @Operation(
            summary = "Get the list of the most profitable books for a library"
    )
    public Response getProfitableBooksForLibrary(
            @Parameter(
                    description = "The id of the library to get a list of the most profitable books",
                    schema = @Schema(type = SchemaType.NUMBER)
            )
            @QueryParam("libraryId") Integer libraryId
    ) {
        try {
            List<Book> books = this.bookService.getProfitableBooksForLibrary(libraryId);
            return Response.ok().entity(books).build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }
}
