package com.supermap.gaf.jersey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

public class JaxrsStaticViewResource {

    @GET
    @Path("{prePath:.*}/static/{path:.*}")
    @Produces( { "text/html", "text/javascript", "application/javascript", "text/css", "image/png", "image/gif", "image/bmp", "image/jpeg", "application/x-woff", "application/vnd.ms-fontobject", "image/svg+xml", "application/x-font-ttf","application/octet-stream" })
    public Response get(@PathParam("path") String path, @Context HttpServletRequest requset, @Context HttpServletResponse response) {
        return new JaxrsStaticResource().get(path, requset, response);
    }
    
    @GET
    @Path("/{path:.*}")
    @Produces( { "text/html", "text/javascript", "application/javascript", "text/css", "image/png", "image/gif", "image/bmp", "image/jpeg", "application/x-woff", "application/vnd.ms-fontobject", "image/svg+xml", "application/x-font-ttf","application/octet-stream" })
    public Response get2(@PathParam("path") String path, @Context HttpServletRequest requset, @Context HttpServletResponse response) {
        return new JaxrsStaticResource().get(path, requset, response);
    }
}
