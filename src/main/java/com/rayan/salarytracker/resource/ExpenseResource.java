package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.dto.expense.ExpenseInsertDTO;
import com.rayan.salarytracker.dto.expense.ExpenseReadOnlyDTO;
import com.rayan.salarytracker.dto.expense.ExpenseUpdateDTO;
import com.rayan.salarytracker.service.impls.ExpenseService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("api/v1/expenses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ExpenseResource {

    @Inject
    ExpenseService expenseService;


    @POST
    public Response createExpense(ExpenseInsertDTO expense) {
        ExpenseReadOnlyDTO expenseReadOnlyDTO = expenseService.save(expense);
        return Response.status(Response.Status.CREATED).entity(expenseReadOnlyDTO).build();
    }

    @GET
    public Response getAllExpenses() {
        List<ExpenseReadOnlyDTO> expenseReadOnlyDTOList = expenseService.findAllExpenses();
        return Response.status(Response.Status.OK).entity(expenseReadOnlyDTOList).build();
    }

    @GET
    @Path("/{expenseId}")
    public Response getExpenseById(@PathParam("expenseId") Long expenseId) {
        ExpenseReadOnlyDTO expenseReadOnlyDTO = expenseService.findExpenseById(expenseId);
        return Response.status(Response.Status.OK).entity(expenseReadOnlyDTO).build();
    }

    @PUT
    @Path("/{expenseId}")
    public Response updateExpense(@PathParam("expenseId") Long expenseId, ExpenseUpdateDTO expense) {
        ExpenseReadOnlyDTO expenseReadOnlyDTO = expenseService.updateExpense(expenseId, expense);
        return Response.status(Response.Status.OK).entity(expenseReadOnlyDTO).build();
    }

    @DELETE
    @Path("/{expenseId}")
    public Response deleteExpense(@PathParam("expenseId") Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
