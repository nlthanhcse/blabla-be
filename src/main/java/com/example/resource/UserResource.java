package com.example.resource;

import com.example.dto.UserDTO;
import com.example.response.GenericResponse;
import com.example.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

@Path("/api/v1/users")
@RolesAllowed({ "USER", "ADMIN" })
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public GenericResponse<UserDTO> getAuthenticatedUser(@Context SecurityContext securityContext) {
        return GenericResponse.ok(userService.getUserUsername(securityContext.getUserPrincipal().getName()));
    }
}
