package com.example.response;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpStatusClass;

public class GenericResponse<T> {

    private T data;
    private int statusCode;
    private String message;
    private String[] messageDetails;

    public GenericResponse(T data, int statusCode, String message, String[] messageDetails) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;
        this.messageDetails = messageDetails;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(String[] messageDetails) {
        this.messageDetails = messageDetails;
    }

    public static <T> GenericResponse<T> ok(T data) {
        return new GenericResponse<T>(
                data,
                HttpResponseStatus.OK.code(),
                HttpResponseStatus.OK.reasonPhrase(), null
        );
    }

    public static <T> GenericResponse<T> badRequest(String[] messageDetails) {
        return new GenericResponse<T>(
                null,
                HttpResponseStatus.BAD_REQUEST.code(),
                HttpResponseStatus.BAD_REQUEST.reasonPhrase(), messageDetails
        );
    }

    public static <T> GenericResponse<T> notFound(String[] messageDetails) {
        return new GenericResponse<T>(
                null,
                HttpResponseStatus.NOT_FOUND.code(),
                HttpResponseStatus.NOT_FOUND.reasonPhrase(), messageDetails
        );
    }

    public static <T> GenericResponse<T> unauthorized() {
        return new GenericResponse<T>(
                null,
                HttpResponseStatus.UNAUTHORIZED.code(),
                HttpResponseStatus.UNAUTHORIZED.reasonPhrase(), new String[]{ "Authentication failed" }
        );
    }

    public static <T> GenericResponse<T> forbidden() {
        return new GenericResponse<T>(
                null,
                HttpResponseStatus.FORBIDDEN.code(),
                HttpResponseStatus.FORBIDDEN.reasonPhrase(), new String[]{ "You are not allowed to access this resource" }
        );
    }

    public static <T> GenericResponse<T> internalServerError() {
        return new GenericResponse<T>(
                null,
                HttpResponseStatus.INTERNAL_SERVER_ERROR.code(),
                HttpResponseStatus.INTERNAL_SERVER_ERROR.reasonPhrase(), new String[]{ "Something went wrong, please try again later" }
        );
    }
}
