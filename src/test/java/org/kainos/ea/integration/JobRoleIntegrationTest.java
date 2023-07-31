package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRolesShouldReturnListOfJobRoles() {
        String url = System.getenv("API_URL") + "/api/job_roles";
        List<JobRole> response = APP.client().target(url)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void putJobRoleShouldReturnVoid() {

        JobRoleRequest updatedJobRole = new JobRoleRequest("Bond James Bond", "Updated Job Specification", 1,1, "www.google.com");

        String url = System.getenv("API_URL") + "/api/job_roles/1";
        Response response = APP.client().target(url)
                .request()
                .put(Entity.entity(updatedJobRole, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        JobRoleRequest fetchedUpdatedJobRole = APP.client().target(url)
                .request()
                .get(JobRoleRequest.class);

        Assertions.assertEquals(updatedJobRole.getName(), fetchedUpdatedJobRole.getName());
    }
}
