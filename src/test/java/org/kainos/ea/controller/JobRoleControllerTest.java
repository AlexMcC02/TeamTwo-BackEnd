package org.kainos.ea.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.exception.FailedToDeleteJobRoleException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.service.JobRoleService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleControllerTest {

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRoleController jobRoleController = new JobRoleController(jobRoleService);

    JobRole jobRole = new JobRole(1, "Rocket Scientist", "Einstein-Tier");

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
    void deleteJobShouldReturnOkWhenServiceReturnsVoid() throws FailedToDeleteJobRoleException {
        Mockito.doNothing().when(jobRoleService).deleteJobRole(jobRole.getId());

        Response response = jobRoleController.deleteJobRole(jobRole.getId());
        assert(response.getStatus() == 200);
    }

    @Test
    void deleteJobRoleShouldReturnInternalServerErrorWhenServiceThrowsException() throws FailedToDeleteJobRoleException {

        Mockito.doThrow(FailedToDeleteJobRoleException.class).when(jobRoleService).deleteJobRole(jobRole.getId());

        Response response = jobRoleController.deleteJobRole(jobRole.getId());
        assert(response.getStatus() == 500);
    }
}

