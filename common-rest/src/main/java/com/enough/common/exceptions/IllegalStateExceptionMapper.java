package com.supermap.gaf.rest.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.supermap.gaf.commontypes.MessageResult;
import com.supermap.gaf.rest.HttpServletRequestAware;
import com.supermap.gaf.rest.utils.HttpUtil;

public class IllegalStateExceptionMapper implements HttpServletRequestAware, ExceptionMapper<IllegalStateException> {
    private HttpServletRequest request;

    @Context
    public void setHttpServletRequest(@Context HttpServletRequest request) {
        this.request = request;
    }

    public Response toResponse(IllegalStateException e) {
        ResponseBuilder responseBuilder = Response.serverError();
        String errMessage = e.getMessage() == null ? "IllegalStateException" : e.getMessage();
        MessageResult<String> result = MessageResult.failed(String.class)
                .status(Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .message(errMessage)
                .data(errMessage).build();
        return responseBuilder.header("Content-Type", HttpUtil.getAcceptMediaType(this.request))
                .entity(result).build();
    }
}