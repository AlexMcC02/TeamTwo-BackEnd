package org.kainos.ea.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.service.JobRoleService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleControllerTest {

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    private final JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);

    @BeforeEach
    void setup() {
        Mockito.reset(jobRoleService);
    }

    @Test
    void getJobRolesShouldReturnListOfJobRoles() throws FailedToGetJobRolesException {
        List<JobRole> jobRoles = Arrays.asList(
                new JobRole(1, "Software Engineer", "Does coding. (slowly)"),
                new JobRole(2, "Test Engineer", "Does testing (aka nothing).")
        );

        Mockito.when(jobRoleService.getAllJobRoles()).thenReturn(jobRoles);

        Response response = APP.client().target(System.getenv("API_URL") + "/api/job_roles").request().get();

        assertEquals(200, response.getStatus());
    }

//    @Test
//    void getJobRolesShouldReturnServerError() throws FailedToGetJobRolesException {
//        Mockito.when(jobRoleService.getAllJobRoles()).thenThrow(new FailedToGetJobRolesException());
//        Response response = APP.client().target(System.getenv("DB_URL") + "/api/job_roles").request().get();
//        assertEquals(500, response.getStatus());
//    }
}