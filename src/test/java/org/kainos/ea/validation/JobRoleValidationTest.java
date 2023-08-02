package org.kainos.ea.validation;

import org.junit.jupiter.api.Test;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.validator.JobRoleValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JobRoleValidationTest {

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
    public void isValidJobRoleShouldReturnNameIsTooShortWhenNameIsTooShort() {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "A",
                "This is a specification that is of a sensible length.",
                1,
                1,
                "http://www.google.com"
        );
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "Name is shorter than 2 characters.");
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
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "Name is longer than 50 characters.");
    }

    @Test
    public void isValidJobRoleShouldReturnSpecificationIsTooShortWhenSpecificationIsTooShort() {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Alex McCullough",
                "Short",
                1,
                1,
                "http://www.google.com"
        );
        assertEquals(jobRoleValidator.isValidJobRole(jobRoleRequest), "Specification less than 10 characters.");

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
