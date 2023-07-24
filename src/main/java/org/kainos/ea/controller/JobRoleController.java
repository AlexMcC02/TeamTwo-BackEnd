package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToGetBandLevelException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.util.DatabaseConnector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team Too Project API")
@Path("/api")
public class JobRoleController {

    private final JobRoleService jobRoleService;

    public JobRoleController(JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }

    @GET
    @Path("/job_roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles() {

        try {
            return Response.ok(jobRoleService.getAllJobRoles()).build();
        } catch (FailedToGetJobRolesException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/band_levels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBandLevels() {

        try {
            return Response.ok(jobRoleService.getAllBandLevels()).build();
        } catch (FailedToGetBandLevelException | SQLException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

}
