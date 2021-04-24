package ru.krisnovitskaya.rest;

import ru.krisnovitskaya.service.repr.CategoryRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/category")
public interface CategoryResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryRepr> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryRepr findById(@PathParam("id") long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryRepr categoryRepr);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(CategoryRepr categoryRepr);
}
