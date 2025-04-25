package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.dto.MonthlySummaryDTO;
import com.rayan.salarytracker.service.impls.SummaryService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.YearMonth;

@Path("/api/v1/summary")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class SummaryResource {

    @Inject
    SummaryService summaryService;

    @GET
    @Path("/{yearMonth}")
    public Response getMonthlySummary(@PathParam("yearMonth") String yearMonthStr) {
        YearMonth yearMonth = YearMonth.parse(yearMonthStr);
        MonthlySummaryDTO summary = summaryService.getMonthlySummary(yearMonth);
        return Response.ok(summary).build();
    }
}