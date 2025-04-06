package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.dto.user.AuthenticationResponseDTO;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserLoginDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.security.JwtService;
import com.rayan.salarytracker.service.IUserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/v1/auth")
public class UserResource {

    @Inject
    IUserService userService;

    @POST
    @Path("/register")
    public Response registerUser(@Valid UserInsertDTO userInsertDTO) {
        UserReadOnlyDTO userReadOnlyDTO = userService.createUser(userInsertDTO);

        return Response.status(Response.Status.CREATED)
                .entity(userReadOnlyDTO)
                .build();
    }

    @POST
    @Path("/login")
    public Response loginUser(@Valid UserLoginDTO request) {
        if (!userService.isUserValid(request.getEmail(), request.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        UserReadOnlyDTO user = userService.findUserByEmail(request.getEmail());
        // Generate token
        String token = JwtService.generateToken(user);

        UserReadOnlyDTO readOnlyDTO = userService.findUserByEmail(request.getEmail());
        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO(token, readOnlyDTO);
        return Response.status(Response.Status.OK).entity(authenticationResponseDTO).build();
    }
}
