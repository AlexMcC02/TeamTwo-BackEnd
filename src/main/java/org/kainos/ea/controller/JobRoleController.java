package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.*;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.util.DatabaseConnector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Path("/job_roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecificationId(@PathParam("id") int id) {

        try {
            return Response.ok(jobRoleService.getSpecificationById(id)).build();
        } catch (DatabaseConnectionException e) {
            System.err.println(e.getMessage());

            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        } catch (FailedToFindExistingIdInDb e){


            return Response.status(HttpStatus.NOT_FOUND_404).build();
        }  catch (FailedToGetValidJobId e){
            System.err.println(e.getMessage());

            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }

    }
}
