package com.dk.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by zzy on 15/12/21.
 */
@Path("/sayHello")
public class HelloWorldService {
    @GET
    @Path("/{name}")
    public Response helloWorld( @PathParam("name")String name){
        String respone = "Hello world! My name is "+ name+"zhaozhiyong1223"+name;
        return Response.status(200).entity(respone).build();
    }

}
