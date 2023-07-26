package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetCapabilitiesException;
import org.kainos.ea.service.CapabilityService;
import org.kainos.ea.util.DatabaseConnector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team Too Project API")
@Path("/api")
public class CapabilityController {

    private final CapabilityService capabilityService;

    public CapabilityController(CapabilityService capabilityService) {
        this.capabilityService = capabilityService;
    }

    @GET
    @Path("/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilities() {
        try {
            return Response.ok(capabilityService.getAllCapabilities()).build();
        } catch (FailedToGetCapabilitiesException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }
    }

}
