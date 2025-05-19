package com.example.resource;

import com.example.response.GenericResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

@Path("/api/v1/admin")
@RolesAllowed("ADMIN")
public class AdminResource {

    @GET()
    public GenericResponse<String> getAdminData(@Context SecurityContext securityContext) {
        return GenericResponse.ok(
                "Hallo ADMIN \"" + securityContext.getUserPrincipal().getName() + "\", this is your secret text!!!"
        );
    }
}
