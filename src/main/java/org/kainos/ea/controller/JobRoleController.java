package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToGetCapabilityException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.util.DatabaseConnector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Team Too Project API")
@Path("/api")
public class JobRoleController {

    private static JobRoleService jobRoleService;

    public JobRoleController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        jobRoleService = new JobRoleService(new JobRoleDao(), databaseConnector);
    }

    @GET
    @Path("/job_roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles() {

        try {
            return Response.ok(jobRoleService.getAllJobRoles()).build();
        } catch (FailedToGetJobRolesException | SQLException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/capability")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapability() {

        try {
            return Response.ok(jobRoleService.getCapability()).build();
        } catch (FailedToGetCapabilityException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
