package com.example.resource;

import com.example.response.GenericResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/v1/user")
@RolesAllowed("USER")
public class UserResource {

    @GET
    public GenericResponse<String> getUserData() {
        return GenericResponse.ok("User data");
    }
}
