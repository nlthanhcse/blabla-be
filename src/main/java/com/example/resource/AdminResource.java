package com.example.resource;

import com.example.response.GenericResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/v1/admin")
@RolesAllowed("ADMIN")
public class AdminResource {

    @GET
    public GenericResponse<String> getAdminData() {
        return GenericResponse.ok("Admin data");
    }
}
