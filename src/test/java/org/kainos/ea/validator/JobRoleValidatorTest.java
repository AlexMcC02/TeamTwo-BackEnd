package org.kainos.ea.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JobRoleValidatorTest {

    DatabaseConnector databaseConnector = new DatabaseConnector();
    JobRoleDao jobRoleDao = new JobRoleDao();

    JobRoleValidator jobRoleValidator = new JobRoleValidator();

    @Test
    public void isValidJobRoleShouldReturnTrueWhenValidEmployee() throws DatabaseConnectionException, SQLException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Alex McCullough",
                "This is a specification that is of a sensible length.",
                1,
                1,
                "http://www.google.com"
        );
        assertNull(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRoleShouldReturnNameIsTooShortWhenNameIsEmpty() throws DatabaseConnectionException, SQLException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "",
                "This is a specification that is of a sensible length.",
                1,
                1,
                "http://www.google.com"
        );
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "Name cannot be null or empty.");
    }

    @Test
    public void isValidJobRoleShouldReturnNameIsTooLongWhenNameIsTooLong() throws DatabaseConnectionException, SQLException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Alex McCullough Alex McCullough Alex McCullough Alex McCullough Alex McCullough Alex McCullough",
                "This is a specification that is of a sensible length.",
                1,
                1,
                "http://www.google.com"
        );
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "Name exceeds limit of 50 characters.");
    }


    @Test
    public void isValidJobRoleShouldReturnSpecificationIsTooLongWhenSpecificationIsTooLong() throws DatabaseConnectionException, SQLException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Alex McCullough",
                "This is a specification that is of a not sensible length. This is a specification that " +
                        "is of a not sensible length. This is a specification that is of a not sensible length. This " +
                        "is a specification that is of a not sensible length. This is a specification that is of a " +
                        "not sensible length. This is a specification that is of a not sensible length. This is a " +
                        "specification that is of a not sensible length. This is a specification that is of a not " +
                        "sensible length. This is a specification that is of a not sensible length.",
                1,
                1,
                "http://www.google.com"
        );
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "Specification exceeds limit of 250 characters.");
    }

    @Test
    public void isValidJobRoleShouldReturnCapabilityDoesNotExistWhenCapabilityDoesNotExist() throws DatabaseConnectionException, SQLException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Alex McCullough",
                "This is a specification that is of a sensible length.",
                1,
                50,
                "http://www.google.com"
        );
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "CapabilityID doesn't exist.");
    }

    @Test
    public void isValidJobRoleShouldReturnBandDoesNotExistWhenBandDoesNotExist() throws DatabaseConnectionException, SQLException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Alex McCullough",
                "This is a specification that is of a sensible length.",
                50,
                1,
                "http://www.google.com"
        );
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "BandID doesn't exist.");
    }

}