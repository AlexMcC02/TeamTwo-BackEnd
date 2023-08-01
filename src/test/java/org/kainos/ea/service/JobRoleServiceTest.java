package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRole;
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
    void getJobRolesShouldReturnListOfJobRolesWhenDaoReturnsListOfJobRoles() throws SQLException, FailedToGetJobRolesException, DatabaseConnectionException {
        List<JobRole> expected_result = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getAllJobRoles(conn)).thenReturn(expected_result);

        List<JobRole> result = jobRoleService.getAllJobRoles();

        assertEquals(expected_result, result);
    }

    @Test
    void getJobRolesShouldThrowFailedToGetJobsExceptionWhenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getAllJobRoles(conn)).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobRolesException.class,
                () -> jobRoleService.getAllJobRoles());

    }

    @Test
    void getJobRolesShouldThrowDatabaseConnectionExceptionWhenDaoThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetJobRolesException.class,
                () -> jobRoleService.getAllJobRoles());

    }

    @Test
    void createJobRoleShouldReturnIdWhenDaoCreatesJobRole() throws SQLException, FailedToCreateJobRoleException, InvalidJobRoleException, DatabaseConnectionException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "CEO",
                "Running the show",
                1,
                1,
                "www.google.com");

        int expectedId = 1;

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.createJobRole(jobRoleRequest)).thenReturn(expectedId);

        int resultId = jobRoleService.createJobRole(jobRoleRequest);

        assertEquals(expectedId, resultId);
    }

    @Test
    void createJobRoleShouldThrowFailedToCreateJobRoleExceptionWhenDaoReturnsMinusOne() throws SQLException, DatabaseConnectionException, InvalidJobRoleException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "CEO",
                "Running the show",
                1,
                1,
                "www.google.com");
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.createJobRole(jobRoleRequest)).thenReturn(-1);

        assertThrows(FailedToCreateJobRoleException.class,
                () -> jobRoleService.createJobRole(jobRoleRequest));
    }

    @Test
    void createJobRoleShouldThrowInvalidJobRoleExceptionWhenJobRoleRequestIsInvalid() throws SQLException, DatabaseConnectionException, FailedToCreateJobRoleException {
        JobRoleRequest invalidJobRoleRequest = new JobRoleRequest(
                null,
                "Running the show",
                1,
                1,
                "www.google.com");
        assertThrows(InvalidJobRoleException.class,
                () -> jobRoleService.createJobRole(invalidJobRoleRequest));
    }

    @Test
    void createJobRoleShouldThrowFailedToCreateJobRoleExceptionWhenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException, InvalidJobRoleException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "CEO",
                "Running the show",
                1,
                1,
                "www.google.com");
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.createJobRole(jobRoleRequest)).thenThrow(SQLException.class);

        assertThrows(FailedToCreateJobRoleException.class,
                () -> jobRoleService.createJobRole(jobRoleRequest));
    }

}
