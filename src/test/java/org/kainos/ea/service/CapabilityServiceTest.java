package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetCapabilitiesException;
import org.kainos.ea.model.Capability;
import org.kainos.ea.util.DatabaseConnector;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CapabilityServiceTest {

    CapabilityDao capabilityDao = Mockito.mock(CapabilityDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    CapabilityService capabilityService = new CapabilityService(capabilityDao, databaseConnector);

    Connection conn;

    @Test
    void getCapabilitiesShouldReturnListOfCapabilitiesWhenDaoReturnsListOfCapabilities() throws SQLException, FailedToGetCapabilitiesException, DatabaseConnectionException {

        List<Capability> expectedCapabilities = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityDao.getAllCapabilities(conn)).thenReturn(expectedCapabilities);

        List<Capability> result = capabilityService.getAllCapabilities();

        assertEquals(expectedCapabilities, result);
    }

    @Test
    void getCapabilitiesShouldThrowFailedToGetCapabilitiesExceptionWhenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityDao.getAllCapabilities(conn)).thenThrow(SQLException.class);

        assertThrows(FailedToGetCapabilitiesException.class, () -> capabilityService.getAllCapabilities());
    }

    @Test
    void getCapabilitiesShouldThrowDatabaseConnectionExceptionWhenDaoThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {

        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetCapabilitiesException.class, () -> capabilityService.getAllCapabilities());
    }
}
