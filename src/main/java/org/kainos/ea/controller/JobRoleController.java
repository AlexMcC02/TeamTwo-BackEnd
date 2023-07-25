package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.util.DatabaseConnector;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
        jobRoleService = new JobRoleService(new JobRoleDao(), new DatabaseConnector());
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

    @POST
    @Path("/job_roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobRole(JobRoleRequest jobRole) {
        try {
            return Response.ok(jobRoleService.createJobRole(jobRole)).build();
        } catch (FailedToCreateJobRoleException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidJobRoleException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
}
