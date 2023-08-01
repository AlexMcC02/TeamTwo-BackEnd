package org.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.controller.JobRoleController;
import org.kainos.ea.exception.FailedToFindExistingIdInDb;
import org.kainos.ea.model.JobRole;
import javax.ws.rs.core.Response;
import org.kainos.ea.model.JobRoleSpec;
import org.kainos.ea.service.JobRoleService;
import org.mockito.Mockito;
import java.util.List;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetValidJobId;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRoleController jobRoleController = new JobRoleController(jobRoleService);

    @Test
    void getJobRolesShouldReturnListOfJobRoles() {
        String url = System.getenv("API_URL") + "/api/job_roles";
        List<JobRole> response = APP.client().target(url)
                .request()
                .get(List.class);

        // Parse object.
        ObjectMapper mapper = new ObjectMapper();
        List<JobRole> parsedResponse = mapper.convertValue(response, new TypeReference<List<JobRole>>() {});

        for (int i = 0; i < parsedResponse.size(); i++) {
            Assertions.assertNotNull(parsedResponse.get(i).getCapability());
        }
        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void deleteJobRoleShouldReturnVoid() {
        String url = System.getenv("API_URL") + "/api/job_roles/1";
        Response response = APP.client().target(url)
                .request()
                .delete();

        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    void getJobSpecificationIdShouldReturnOkForValidId() throws DatabaseConnectionException, FailedToGetValidJobId, FailedToFindExistingIdInDb {
        int validId = 1;
        String url = System.getenv("API_URL") + "/api/job_roles/" + validId;
        JobRoleSpec expectedSpec = new JobRoleSpec(validId, "Bond James Bond", "Updated Job Specification", "https://google.com");
        Mockito.when(jobRoleService.getSpecificationById(validId)).thenReturn(expectedSpec);

        Response response = APP.client().target(url)
                .request()
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        JobRoleSpec returnedSpec = response.readEntity(JobRoleSpec.class);

        // Assert that the ID of the spec returned is the same as the one requested in the URL
        assertEquals(expectedSpec.getId(), returnedSpec.getId());
    }

    @Test
    void getJobSpecificationIdShouldReturnBadRequestForNegativeId() throws FailedToGetValidJobId, FailedToFindExistingIdInDb, DatabaseConnectionException {
        int invalidId = -1;
        String url = System.getenv("API_URL") + "/api/job_roles/" + invalidId;
        Mockito.doThrow(FailedToGetValidJobId.class).when(jobRoleService).getSpecificationById(invalidId);

        Response response = APP.client().target(url)
                .request()
                .get();

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void getJobSpecificationIdShouldReturnNotFoundForInvalidId()  {
        int invalidId = 99999999;
        String getEmployeeUrl = System.getenv("API_URL") + "/api/job_roles/" + invalidId;
        Response response = APP.client().target(getEmployeeUrl)
                .request()
                .get();

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    @Test
    void getJobSpecificationIdShouldReturnNotFoundForNonExistingId() throws FailedToGetValidJobId, FailedToFindExistingIdInDb, DatabaseConnectionException {
        int nonExistingId = 999;
        String url = System.getenv("API_URL") + "/api/job_roles/" + nonExistingId;
        Mockito.doThrow(FailedToFindExistingIdInDb.class).when(jobRoleService).getSpecificationById(nonExistingId);

        Response response = APP.client().target(url)
                .request()
                .get();

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}
