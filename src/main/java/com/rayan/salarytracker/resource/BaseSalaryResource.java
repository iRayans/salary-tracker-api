package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.dto.basesalary.BaseSalaryInsertDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryReadOnlyDTO;
import com.rayan.salarytracker.service.IBaseSalaryService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/salaries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class BaseSalaryResource {
    @Inject
    IBaseSalaryService baseSalaryService;


    @POST
    public Response createBaseSalary(BaseSalaryInsertDTO baseSalaryInsertDTO) {
        BaseSalaryReadOnlyDTO baseSalaryReadOnlyDTO = baseSalaryService.save(baseSalaryInsertDTO);
        System.out.println("Created BaseSalary: " + baseSalaryReadOnlyDTO);
        return Response.status(Response.Status.CREATED).entity(baseSalaryReadOnlyDTO).build();
    }
}

