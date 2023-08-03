package org.kainos.ea.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardWebServiceApplication;
import org.kainos.ea.DropwizardWebServiceConfiguration;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetCapabilitiesException;
import org.kainos.ea.model.Capability;
import org.kainos.ea.service.CapabilityService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CapabilityControllerTest {

    static final DropwizardAppExtension<DropwizardWebServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardWebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    CapabilityService capabilityService = Mockito.mock(CapabilityService.class);
    CapabilityController capabilityController = new CapabilityController(capabilityService);

    Capability capability = new Capability(1, "Sample Capability");

    @Test
    void getCapabilities_shouldReturnOK_whenServiceReturnsList() throws FailedToGetCapabilitiesException, DatabaseConnectionException {

        List<Capability> sampleCapabilities = new ArrayList<>();
        sampleCapabilities.add(capability);
        sampleCapabilities.add(capability);
        sampleCapabilities.add(capability);

        Mockito.when(capabilityService.getAllCapabilities()).thenReturn(sampleCapabilities);

        Response response = capabilityController.getAllCapabilities();
        assertEquals(200, response.getStatus());
    }

    @Test
    void getCapabilities_shouldReturnInternalServerError_whenServiceThrowsException() throws FailedToGetCapabilitiesException, DatabaseConnectionException {

        Mockito.when(capabilityService.getAllCapabilities()).thenThrow(FailedToGetCapabilitiesException.class);

        Response response = capabilityController.getAllCapabilities();
        assertEquals(500, response.getStatus());
    }

    @Test
    void getCapabilities_shouldThrowRuntimeException_whenServiceThrowsDatabaseConnectionException() throws FailedToGetCapabilitiesException, DatabaseConnectionException {

        Mockito.when(capabilityService.getAllCapabilities()).thenThrow(DatabaseConnectionException.class);

        assertThrows(RuntimeException.class, () -> capabilityController.getAllCapabilities());

        Mockito.verify(capabilityService, Mockito.times(1)).getAllCapabilities();
    }
}

