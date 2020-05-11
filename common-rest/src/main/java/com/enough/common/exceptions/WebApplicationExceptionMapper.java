package com.supermap.gaf.rest.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.supermap.gaf.commontypes.MessageResult;
import com.supermap.gaf.rest.HttpServletRequestAware;
import com.supermap.gaf.rest.utils.HttpUtil;

public class WebApplicationExceptionMapper implements HttpServletRequestAware, ExceptionMapper<WebApplicationException> {
    
    private HttpServletRequest request;
    
    @Override
    public void setHttpServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    @Override
    public Response toResponse(WebApplicationException ex) {
        Response response = ex.getResponse();
        String errMessage = ex.getMessage() == null ? "webApplicaitonExceptioni" : ex.getMessage();
        MessageResult<String> result = MessageResult.failed(String.class)
                .status(response.getStatus())
                .message(errMessage)
                .data(errMessage).build();
        return Response.status(response.getStatus())
                .header("Content-Type", HttpUtil.getAcceptMediaType(this.request))
                .entity(result)
                .build();
    }
}
