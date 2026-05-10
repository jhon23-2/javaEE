package com.demo.customer.war.controller;

import com.demo.customer.ejb.CustomerServiceBean;
import com.demo.customer.util.dto.CustomerRequestDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {

    @Inject
    private CustomerServiceBean customerServiceBean;


    @POST
    public Response create (CustomerRequestDTO request) {
        if (request == null) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.status(Response.Status.CREATED).entity(this.customerServiceBean.saveCustomer(request))
                .build();
    }

    @GET
    public Response findAll() {
        return Response.accepted()
                .entity(this.customerServiceBean.findAllCustomer())
                .build();
    }
}
