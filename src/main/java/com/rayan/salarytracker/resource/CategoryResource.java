package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.model.Category;
import com.rayan.salarytracker.service.ICategoryService;
import com.rayan.salarytracker.service.impls.CategoryService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("api/v1/categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class CategoryResource {

    @Inject
    ICategoryService categoryService;

    @POST
    public Response createCategory(@Valid Category category) {
        Category cat = categoryService.save(category);
        return Response.status(Response.Status.CREATED).entity(cat).build();
    }

    @GET
    public Response getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return Response.status(Response.Status.OK).entity(categories).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategoryById(@PathParam("id") Long id) {
        Category category = categoryService.findById(id);
        return Response.status(Response.Status.OK).entity(category).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCategory(@PathParam("id") Long id, @Valid Category categoryUpdate) {
        Category category = categoryService.update(id, categoryUpdate);
        return Response.status(Response.Status.OK).entity(category).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategoryById(@PathParam("id") Long id) {
        categoryService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
