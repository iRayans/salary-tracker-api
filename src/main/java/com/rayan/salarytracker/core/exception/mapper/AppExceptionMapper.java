package com.rayan.salarytracker.core.exception.mapper;


import com.rayan.salarytracker.core.exception.*;
import com.rayan.salarytracker.dto.ErrorMessageDTO;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

/**
 * Maps application exceptions to appropriate HTTP responses
 */
@Provider
public class AppExceptionMapper implements ExceptionMapper<GenericException> {

    private static final Logger LOG = Logger.getLogger(AppExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(GenericException exception) {
        // Default status
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        // Determine the appropriate HTTP status based on exception type
        if (exception instanceof EntityInvalidArgumentsException) {
            status = Response.Status.BAD_REQUEST;
        } else if (exception instanceof EntityAlreadyExistsException) {
            status = Response.Status.CONFLICT;
        } else if (exception instanceof EntityNotFoundException) {
            status = Response.Status.NOT_FOUND;
        } else if (exception instanceof AppServerException) {
            status = Response.Status.SERVICE_UNAVAILABLE;
        }

        // Log the exception
        if (status == Response.Status.INTERNAL_SERVER_ERROR) {
            LOG.error("Unhandled exception", exception);
        } else {
            LOG.info("API exception: " + status + " - " + exception.getMessage());
        }

        // Build and return the error response
        return Response
                .status(status)
                .entity(new ErrorMessageDTO(
                        exception.getCode(),
                        exception.getMessage(),
                        uriInfo != null ? uriInfo.getPath() : "unknown"
                ))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}