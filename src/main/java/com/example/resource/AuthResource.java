package com.example.resource;

import com.example.config.Startup;
import com.example.dto.AuthDTO;
import com.example.request.LoginRequest;
import com.example.response.GenericResponse;
import com.example.service.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/auth")
public class AuthResource {

    private final AuthService authService;
    private final Startup startup;

    public AuthResource(AuthService authService, Startup startup) {
        this.authService = authService;
        this.startup = startup;
    }

    @POST
    @Path(value = "/login")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public GenericResponse<AuthDTO> login(LoginRequest loginRequest) throws Exception {
        return GenericResponse.ok(
                authService.login(loginRequest)
        );
    }

    @GET
    @Path(value = "/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public GenericResponse<AuthDTO> logout() {
        return GenericResponse.ok(
                null
        );
    }
}
