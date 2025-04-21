package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseInsertDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseReadOnlyDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseUpdateDTO;
import com.rayan.salarytracker.service.IRecurringExpenseService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("api/v1/recurringExpenses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class RecurringExpenseResource {

    @Inject
    IRecurringExpenseService recurringExpenseService;

    @POST
    public Response create(RecurringExpenseInsertDTO recurringExpenseInsertDTO) {
        RecurringExpenseReadOnlyDTO readOnlyDTO = recurringExpenseService.save(recurringExpenseInsertDTO);
        return Response.status(Response.Status.CREATED).entity(readOnlyDTO).build();
    }

    @GET
    public Response getAll() {
        List<RecurringExpenseReadOnlyDTO> recurringExpenses = recurringExpenseService.findAll();
        return Response.status(Response.Status.OK).entity(recurringExpenses).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        RecurringExpenseReadOnlyDTO recurringExpense = recurringExpenseService.findById(id);
        return Response.status(Response.Status.OK).entity(recurringExpense).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Long id, RecurringExpenseUpdateDTO recurringExpenseUpdateDTO) {
        RecurringExpenseReadOnlyDTO recurringExpense = recurringExpenseService.update(id, recurringExpenseUpdateDTO);
        return Response.status(Response.Status.OK).entity(recurringExpense).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        recurringExpenseService.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
