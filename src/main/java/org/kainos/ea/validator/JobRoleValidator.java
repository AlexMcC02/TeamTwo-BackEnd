package org.kainos.ea.validator;

import org.kainos.ea.cli.JobRoleRequest;

public class JobRoleValidator {
    public String isValidJobRole(JobRoleRequest jobRole) {
        if (jobRole.getName() == null || jobRole.getName().isEmpty()) {
            return "Name cannot be null or empty";
        }

        if (jobRole.getName().length() > 50) {
            return "Name greater than 50 characters";
        }

        if (jobRole.getSpecification().length()> 100) {
            return "Specification less than 100 characters";
        }
         return null;
    }
}