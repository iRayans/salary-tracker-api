package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.model.Expense;
import com.rayan.salarytracker.service.impls.ExpenseGenerationService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Path("/api/v1/expenses/generate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ExpenseGenerationResource {

    @Inject
    ExpenseGenerationService generationService;

    @POST
    public Response generateExpenses(YearMonthDTO yearMonthDTO) {
        YearMonth targetMonth = YearMonth.parse(yearMonthDTO.getYearMonth());
        List<Expense> generatedExpenses = generationService.generateExpensesForMonth(targetMonth);

        return Response.status(Response.Status.CREATED)
                .entity(Map.of(
                        "message", "Generated " + generatedExpenses.size() + " expenses",
                        "count", generatedExpenses.size()
                ))
                .build();
    }

    public static class YearMonthDTO {
        private String yearMonth;

        // Getters and setters
        public String getYearMonth() {
            return yearMonth;
        }

        public void setYearMonth(String yearMonth) {
            this.yearMonth = yearMonth;
        }
    }
}