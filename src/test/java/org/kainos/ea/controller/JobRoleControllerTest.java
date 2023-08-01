package org.kainos.ea.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToFindExistingIdInDb;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.FailedToGetValidJobId;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleSpec;
import org.kainos.ea.service.JobRoleService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleControllerTest {

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRoleController jobRoleController = new JobRoleController(jobRoleService);

    JobRole jobRole = new JobRole(1, "Rocket Scientist", "Einstein-Tier", "Trainee", "Digital Services");

    @Test
    void getJobRolesShouldReturnOKWhenServiceReturnsList() throws FailedToGetJobRolesException {

        List<JobRole> sampleJobRoles = new ArrayList<>();
        sampleJobRoles.add(jobRole);
        sampleJobRoles.add(jobRole);
        sampleJobRoles.add(jobRole);

        Mockito.when(jobRoleService.getAllJobRoles()).thenReturn(sampleJobRoles);

        Response response = jobRoleController.getAllJobRoles();
        assert (response.getStatus() == 200);
    }

    @Test
    void getJobRolesShouldReturnInternalServerErrorWhenServiceThrowsException() throws FailedToGetJobRolesException {

        Mockito.when(jobRoleService.getAllJobRoles()).thenThrow(FailedToGetJobRolesException.class);

        Response response = jobRoleController.getAllJobRoles();
        assert (response.getStatus() == 500);
    }

    @Test
    void getJobSpecificationIdShouldReturnOkForValidId() throws DatabaseConnectionException, FailedToGetValidJobId, FailedToFindExistingIdInDb {
        int validId = 1;
        JobRoleSpec expectedSpec = new JobRoleSpec(validId, "Software Engineer", "Does coding.", "https://google.com");
        Mockito.when(jobRoleService.getSpecificationById(validId)).thenReturn(expectedSpec);

        Response response = jobRoleController.getJobSpecificationId(validId);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expectedSpec, response.getEntity());
    }
    @Test
    void getJobSpecificationIdShouldReturnBadRequestForNegativeId() throws FailedToGetValidJobId, FailedToFindExistingIdInDb, DatabaseConnectionException {
        int invalidId = -1;
        Mockito.doThrow(FailedToGetValidJobId.class).when(jobRoleService).getSpecificationById(invalidId);

        Response response = jobRoleController.getJobSpecificationId(invalidId);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    @Test
    void getJobSpecificationIdShouldReturnNotFoundForNonExistingId() throws FailedToGetValidJobId, FailedToFindExistingIdInDb, DatabaseConnectionException {
        int nonExistingId = 99999999;
        Mockito.doThrow(FailedToFindExistingIdInDb.class).when(jobRoleService).getSpecificationById(nonExistingId);

        Response response = jobRoleController.getJobSpecificationId(nonExistingId);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    @Test
    void getJobSpecificationIdShouldReturnInternalServerErrorOnDatabaseConnectionError() throws FailedToGetValidJobId, FailedToFindExistingIdInDb, DatabaseConnectionException {
        int validId = 1;
        Mockito.doThrow(DatabaseConnectionException.class).when(jobRoleService).getSpecificationById(validId);

        Response response = jobRoleController.getJobSpecificationId(validId);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}