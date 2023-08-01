package org.kainos.ea.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRole;
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

    JobRole jobRole = new JobRole(1, "Rocket Scientist", "Einstein-Tier", "https://www.prnewswire.com/news-releases/it-works");

    @Test
    void getJobRoles_shouldReturnOK_whenServiceReturnsList() throws FailedToGetJobRolesException {

        List<JobRole> sampleJobRoles = new ArrayList<>();
        sampleJobRoles.add(jobRole);
        sampleJobRoles.add(jobRole);
        sampleJobRoles.add(jobRole);

        Mockito.when(jobRoleService.getAllJobRoles()).thenReturn(sampleJobRoles);

        Response response = jobRoleController.getAllJobRoles();
        assert (response.getStatus() == 200);
    }

    @Test
    void getJobRoles_shouldReturnInternalServerError_whenServiceThrowsException() throws FailedToGetJobRolesException {

        Mockito.when(jobRoleService.getAllJobRoles()).thenThrow(FailedToGetJobRolesException.class);

        Response response = jobRoleController.getAllJobRoles();
        assert (response.getStatus() == 500);
    }


    @Test
    void createJobRoles_shouldReturnOK_whenServiceCreatesJobRole() throws FailedToCreateJobRoleException, InvalidJobRoleException, InvalidJobRoleException, FailedToCreateJobRoleException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "CEO",
                "Being the bossman",
                1,
                1,
                "www.google.com");
        Mockito.when(jobRoleService.createJobRole(jobRoleRequest)).thenReturn(jobRole.getId());

        Response response = jobRoleController.createJobRole(jobRoleRequest);
        assertEquals(200, response.getStatus());
    }

    @Test
    void createJobRoles_shouldReturnBadRequest_whenServiceThrowsInvalidJobRoleException() throws FailedToCreateJobRoleException, InvalidJobRoleException {

        JobRoleRequest invalidJobRoleRequest = new JobRoleRequest(
                null,
                "Being the bossman",
                1,
                1,
                "www.google.com");

        Mockito.when(jobRoleService.createJobRole(invalidJobRoleRequest)).thenThrow(InvalidJobRoleException.class);

        Response response = jobRoleController.createJobRole(invalidJobRoleRequest);
        assertEquals(400, response.getStatus());
    }

    @Test
    void createJobRoles_shouldReturnInternalServerError_whenServiceThrowsFailedToCreateJobRoleException() throws FailedToCreateJobRoleException, InvalidJobRoleException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "CEO",
                "Being the bossman",
                1,
                1,
                "www.google.com");
        Mockito.when(jobRoleService.createJobRole(jobRoleRequest)).thenThrow(FailedToCreateJobRoleException.class);

        Response response = jobRoleController.createJobRole(jobRoleRequest);
        assertEquals(500, response.getStatus());
    }
}

