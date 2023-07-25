package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;

import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {
    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    String url = System.getenv("API_URL") + "/api/job_roles";
    String Url = System.getenv("API_URL") + "/api/capability";

    @Test
    void getJobRolesShouldReturnListOfJobRoles() {
        List<JobRole> response = APP.client().target(url)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getCapabilityShouldReturnListOfCapabilities() {
        List<Capability> response = APP.client().target(Url)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }
}