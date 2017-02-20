package com.zhangry.service.dubbo;

/**
 * Created by zhangry on 2017/2/20.
 */
public class CustomExceptionMapper implements ExceptionMapper<Exception> {
    public CustomExceptionMapper() {
    }

    public Response toResponse(Exception e) {
        RestResponse restResponse = RestResponse.failure(e.getMessage());
        return e instanceof NotFoundException?Response.status(Status.NOT_FOUND).entity(restResponse).type("application/json; charset=UTF-8").build():Response.status(Status.INTERNAL_SERVER_ERROR).entity(restResponse).type("application/json; charset=UTF-8").build();
    }
}