package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.exception.FailedToGetBandsException;
import org.kainos.ea.model.Band;
import org.kainos.ea.service.BandService;
import org.kainos.ea.util.DatabaseConnector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team Too Project API")
@Path("/api")
public class BandController {

    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GET
    @Path("/band")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBands() {

        try {
            return Response.ok(bandService.getAllBands()).build();
        } catch (FailedToGetBandsException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}