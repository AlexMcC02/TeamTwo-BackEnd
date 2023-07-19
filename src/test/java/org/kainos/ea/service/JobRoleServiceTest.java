package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToGetCapabilityException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.service.JobRoleService;
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
public class JobRoleServiceTest {

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao, databaseConnector);

    Connection conn;

    @Test
    void getJobRoles_shouldReturnListOfJobRoles_whenDaoReturnsListOfJobRoles() throws SQLException, FailedToGetJobRolesException {
        List<JobRole> expected_result = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getAllJobRoles(conn)).thenReturn(expected_result);

        List<JobRole> result = jobRoleService.getAllJobRoles();

        assertEquals(expected_result, result);
    }

    @Test
    void getJobRoles_shouldThrowSQLException_whenDaoThrowsSQLException() throws SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getAllJobRoles(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.getAllJobRoles());
    }

    @Test
    void getCapability_shouldReturnListOfCapabilities_whenDaoReturnsListOfCapabilities() throws SQLException, FailedToGetCapabilityException {
        List<Capability> expected_result = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getCapability(conn)).thenReturn(expected_result);

        List<Capability> result = jobRoleService.getCapability();

        assertEquals(expected_result, result);
    }

    @Test
    void getCapability_shouldThrowSQLException_whenDaoThrowsSQLException() throws SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getCapability(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.getCapability());
    }

}