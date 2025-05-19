package com.example.exception;

import com.example.response.GenericResponse;
import io.quarkus.security.ForbiddenException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(ForbiddenException e) {
        return Response
                .status(GenericResponse.forbidden().getStatusCode())
                .entity(GenericResponse.forbidden())
                .build();
    }
}
