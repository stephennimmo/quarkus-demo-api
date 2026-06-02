package com.stephennimmo.quarkusdemoapi.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        return Response.ok("Hello, World!").build();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloName(@PathParam("name") String name) {
        return Response.ok("Hello, " + name + "!").build();
    }
    
}
