package com.example.exception;

import com.example.response.GenericResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {

        GenericResponse<Object> response = GenericResponse.internalServerError();

        if (e instanceof BadCredentialsException) {
            response = GenericResponse.unauthorized();
        }

        return Response
                .status(response.getStatusCode())
                .entity(response)
                .build();
    }
}
