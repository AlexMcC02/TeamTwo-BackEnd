package org.kainos.ea.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobRoleRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JobRoleValidatorTest {

    JobRoleValidator jobRoleValidator = new JobRoleValidator();

    @Test
    public void isValidJobRoleShouldReturnTrueWhenValidEmployee() {
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
    public void isValidJobRoleShouldReturnNameIsTooShortWhenNameIsEmpty() {
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
    public void isValidJobRoleShouldReturnNameIsTooLongWhenNameIsTooLong() {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Alex McCullough Alex McCullough Alex McCullough Alex McCullough Alex McCullough Alex McCullough",
                "This is a specification that is of a sensible length.",
                1,
                1,
                "http://www.google.com"
        );
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "Name greater than 50 characters.");
    }


    @Test
    public void isValidJobRoleShouldReturnSpecificationIsTooLongWhenSpecificationIsTooLong() {
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
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "Specification more than 250 characters.");
    }

}