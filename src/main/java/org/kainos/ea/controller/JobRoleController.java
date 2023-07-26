package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.FailedToUpdateJobRoleException;
import org.kainos.ea.exception.JobRoleDoesNotExistException;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.service.JobRoleService;

import javax.ws.rs.*;
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
    @Path("/job_roles{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoleByID(@PathParam("id") int id) {

        try {
            return Response.ok(jobRoleService.getJobRoleById(id)).build();
        } catch (JobRoleDoesNotExistException | FailedToGetJobRolesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/job_roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJobRole(@PathParam("id") int id, JobRoleRequest jobRole) {

        try {
            jobRoleService.updateJobRole(id, jobRole);
            return Response.ok().build();
        } catch (JobRoleDoesNotExistException | FailedToUpdateJobRoleException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

}
