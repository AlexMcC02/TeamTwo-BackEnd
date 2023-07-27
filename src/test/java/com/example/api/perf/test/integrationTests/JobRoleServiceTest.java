package com.example.api.perf.test.integrationTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetValidJobId;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleSpec;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.util.DatabaseConnector;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {
    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao, databaseConnector);

    Connection conn;



    @Test
    public void testGetSpecificationById_ValidId_ReturnsJobRoleSpec() throws FailedToGetValidJobId, SQLException, DatabaseConnectionException {
        // Arrange
        int id = 1;
        JobRoleSpec expectedSpec = new JobRoleSpec(id, "Software Engineer", "Does coding.", "https://google.com");
        // Assuming you have a mock/stub implementation of JobRoleDao
        Mockito.when(jobRoleDao.getSpecificationById(id, databaseConnector.getConnection())).thenReturn(expectedSpec);

        // Act
        JobRoleSpec actualSpec = jobRoleService.getSpecificationById(id); // Use jobRoleService instance here

        // Assert
        Assert.assertEquals(expectedSpec, actualSpec);
    }

    @Test(expected = FailedToGetValidJobId.class)
    public void testGetSpecificationById_InvalidId_ThrowsFailedToGetValidJobId() throws FailedToGetValidJobId {
        // Arrange
        int id = -1;

        // Act and Assert: Exception is expected to be thrown
        jobRoleService.getSpecificationById(id); // Use jobRoleService instance here
    }

    @Test(expected = FailedToGetValidJobId.class)
    public void testGetSpecificationById_IdNotFound_ThrowsFailedToGetValidJobId() throws FailedToGetValidJobId, SQLException, DatabaseConnectionException {
        // Arrange
        int id = 100;
        // Assuming you have a mock/stub implementation of JobRoleDao
        Mockito.when(jobRoleDao.getSpecificationById(id, databaseConnector.getConnection())).thenReturn(null);

        // Act and Assert: Exception is expected to be thrown
        jobRoleService.getSpecificationById(id); // Use jobRoleService instance here
    }

    @Test(expected = FailedToGetValidJobId.class)
    public void testGetSpecificationById_DatabaseException_ThrowsFailedToGetValidJobId() throws FailedToGetValidJobId, SQLException, DatabaseConnectionException {
        // Arrange
        int id = 5;
        // Assuming you have a mock/stub implementation of JobRoleDao

        Mockito.when(jobRoleDao.getSpecificationById(id, databaseConnector.getConnection())).thenThrow(new SQLException("Database connection error"));

        // Act
        jobRoleService.getSpecificationById(id); // Use jobRoleService instance here

        // Assert: Exception is expected to be thrown
    }

    @Test(timeout = 1000)
    public void testGetSpecificationById_PerformanceTesting() throws FailedToGetValidJobId, SQLException, DatabaseConnectionException {
        // Arrange
        int id = 1000000;
        JobRoleSpec expectedSpec = new JobRoleSpec(id, "Software Engineer", "Does coding.", "https://google.com");
        // Assuming you have a mock/stub implementation of JobRoleDao
        Mockito.when(jobRoleDao.getSpecificationById(id, databaseConnector.getConnection())).thenReturn(expectedSpec);

        // Act
        JobRoleSpec actualSpec = jobRoleService.getSpecificationById(id); // Use jobRoleService instance here

        // Assert
        Assert.assertEquals(expectedSpec, actualSpec);
    }
}

