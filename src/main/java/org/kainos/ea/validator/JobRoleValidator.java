package org.kainos.ea.validator;

import org.kainos.ea.cli.JobRoleRequest;

public class JobRoleValidator {
    public String isValidJobRole(JobRoleRequest jobRole) {
        if (jobRole.getName().length() > 50) {
            return "Name greater than 50 characters";
        }

        if (jobRole.getSpecification().length()> 100) {
            return "Specification less than 100 characters";
        }
// validation for Band & capability below
//        if (jobRole.getBandId().equals()()) {
//            return "Band ID must not be empty";
//        }
//
//        if (jobRole.getCapabilityId().isEmpty()) {
//            return "Capability ID must not be empty";
//        }
         return null;
    }
}