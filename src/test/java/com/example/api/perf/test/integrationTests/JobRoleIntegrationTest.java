
package com.example.api.perf.test.integrationTests;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.model.JobRole;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {
    String webServer = "http://localhost:8080";

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    public void testGetJobSpecificationId() {
        // Request with valid id

        Response response = ClientBuilder.newClient()
                .target(webServer + "/api/job_roles/1")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getEntity());

        // Request with invalid id
        response = ClientBuilder.newClient()
                .target(webServer + "/api/job_roles/invalidId")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(404, response.getStatus());
    }
}
