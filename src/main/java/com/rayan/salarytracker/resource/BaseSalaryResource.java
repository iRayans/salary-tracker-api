package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.dto.basesalary.BaseSalaryInsertDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryUpdateDTO;
import com.rayan.salarytracker.service.IBaseSalaryService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("api/v1/salaries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class BaseSalaryResource {
    @Inject
    IBaseSalaryService baseSalaryService;


    @POST
    public Response createBaseSalary(@Valid BaseSalaryInsertDTO baseSalaryInsertDTO) {
        BaseSalaryReadOnlyDTO baseSalaryReadOnlyDTO = baseSalaryService.save(baseSalaryInsertDTO);
        return Response.status(Response.Status.CREATED).entity(baseSalaryReadOnlyDTO).build();
    }

    @GET
    public Response getBaseSalaries() {
        List<BaseSalaryReadOnlyDTO> baseSalaries = baseSalaryService.getSalaries();
        return Response.status(Response.Status.OK).entity(baseSalaries).build();
    }

    @GET
    @Path("/{salaryId}")
    public Response getBaseSalaryById(@PathParam("salaryId") Long id) {
        BaseSalaryReadOnlyDTO baseSalaryReadOnlyDTO = baseSalaryService.getById(id);
        return Response.status(Response.Status.OK).entity(baseSalaryReadOnlyDTO).build();
    }

    @PUT
    @Path("/{salaryId}")
    public Response updateBaseSalary(@PathParam("salaryId") Long salaryId, BaseSalaryUpdateDTO baseSalaryUpdateDTO) {
        BaseSalaryReadOnlyDTO baseSalaryReadOnlyDTO = baseSalaryService.updateSalary(salaryId, baseSalaryUpdateDTO);
        return Response.status(Response.Status.OK).entity(baseSalaryReadOnlyDTO).build();
    }

    @DELETE
    @Path("/{salaryId}")
    public Response deleteBaseSalary(@PathParam("salaryId") Long salaryId) {
        baseSalaryService.deleteSalary(salaryId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

