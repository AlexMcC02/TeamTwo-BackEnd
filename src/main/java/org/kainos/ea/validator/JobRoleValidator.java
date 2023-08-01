package org.kainos.ea.validator;

import org.kainos.ea.model.JobRoleRequest;

public class JobRoleValidator {
    public String isValidJobRole(JobRoleRequest jobRole) {

        if (jobRole.getName().length() <= 2) {
            return "Name greater than 2 characters";
        }

        if (jobRole.getName().length() >= 50) {
            return "Name greater than 50 characters";
        }

        if (jobRole.getSpecification().length() < 10) {
            return "Specification less than 10 characters";
        }

        if (jobRole.getSpecification().length() > 250) {
            return "Specification more than 250 characters";
        }

        return null;
    }
}