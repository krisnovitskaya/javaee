package ru.krisnovitskaya.rest;

import ru.krisnovitskaya.service.repr.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepr findById(@PathParam("id") long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductRepr productRepr);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductRepr productRepr);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);

    @GET
    @Path("/find_by_name")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findByName(@QueryParam("name") String name);

    @GET
    @Path("/find_by_category")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findByCategory(@QueryParam("category") Long categoryId);
}
