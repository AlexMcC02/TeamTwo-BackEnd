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
import org.kainos.ea.model.JobRole;
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

        // Parse object.
        ObjectMapper mapper = new ObjectMapper();
        List<JobRole> parsedResponse = mapper.convertValue(response, new TypeReference<List<JobRole>>() {});

        for (int i = 0; i < parsedResponse.size(); i++) {
            Assertions.assertNotNull(parsedResponse.get(i).getCapability());
        }
        for (int i = 0; i < parsedResponse.size(); i++) {
            Assertions.assertNotNull(parsedResponse.get(i).getBandLevel());
        }
        Assertions.assertTrue(response.size() > 0);
    }
}
