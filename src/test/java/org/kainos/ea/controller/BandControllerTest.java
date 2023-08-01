package org.kainos.ea.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.BandController;
import org.kainos.ea.exception.FailedToGetBandsException;
import org.kainos.ea.model.Band;
import org.kainos.ea.service.BandService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BandControllerTest {

    @Mock
    private BandService bandService;

    @InjectMocks
    private BandController bandController;

    @Test
    void getAllBandsShouldReturnListOfBandsWhenServiceReturnsListOfBands() throws FailedToGetBandsException {

        List<Band> expectedBands = new ArrayList<>();
        expectedBands.add(new Band(1, "Band 1"));
        expectedBands.add(new Band(2, "Band 2"));

        when(bandService.getAllBands()).thenReturn(expectedBands);

        Response response = bandController.getAllBands();

        assertEquals(200, response.getStatus());
        assertEquals(expectedBands, response.getEntity());
    }

    @Test
    void getAllBandsShouldReturnInternalServerErrorWhenServiceThrowsException() throws FailedToGetBandsException {

        when(bandService.getAllBands()).thenThrow(FailedToGetBandsException.class);

        Response response = bandController.getAllBands();

        assertEquals(500, response.getStatus());
    }
}
