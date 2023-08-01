package org.kainos.ea.service;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetBandsException;
import org.kainos.ea.model.Band;
import org.kainos.ea.service.BandService;
import org.kainos.ea.util.DatabaseConnector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BandServiceTest {

    @Mock
    private BandDao bandDao;

    @Mock
    private DatabaseConnector databaseConnector;

    @InjectMocks
    private BandService bandService;

    @Test
    void getAllBandsShouldReturnListOfBandsWhenDaoReturnsListOfBands() throws SQLException, FailedToGetBandsException, DatabaseConnectionException {

        List<Band> expectedBands = new ArrayList<>();
        expectedBands.add(new Band(1, "Band 1"));
        expectedBands.add(new Band(2, "Band 2"));

        Connection conn = Mockito.mock(Connection.class);
        when(databaseConnector.getConnection()).thenReturn(conn);
        when(bandDao.getAllBands(conn)).thenReturn(expectedBands);

        List<Band> result = bandService.getAllBands();

        assertEquals(expectedBands, result);
    }

    @Test
    void getAllBandsShouldThrowFailedToGetBandsExceptionWhenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {

        Connection conn = Mockito.mock(Connection.class);
        when(databaseConnector.getConnection()).thenReturn(conn);
        when(bandDao.getAllBands(conn)).thenThrow(SQLException.class);

        assertThrows(FailedToGetBandsException.class, () -> bandService.getAllBands());
    }

    @Test
    void getAllBandsShouldThrowFailedToGetBandsExceptionWhenDaoThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {

        when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetBandsException.class, () -> bandService.getAllBands());
    }
}

