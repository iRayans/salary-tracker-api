package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseInsertDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseReadOnlyDTO;
import com.rayan.salarytracker.service.IRecurringExpenseService;
import com.rayan.salarytracker.service.impls.RecurringExpenseService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
}
