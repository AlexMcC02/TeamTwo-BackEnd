package org.kainos.ea.validator;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;

public class JobRoleValidator {

    DatabaseConnector databaseConnector = new DatabaseConnector();
    JobRoleDao jobRoleDao = new JobRoleDao();

    public String isValidJobRole(JobRoleRequest jobRole) throws DatabaseConnectionException, SQLException {

        ArrayList<Integer> capabilityIds = jobRoleDao.getAllCapabilityIds(databaseConnector.getConnection());
        ArrayList<Integer> bandIds = jobRoleDao.getAllBandIds(databaseConnector.getConnection());

        if (jobRole.getName() == null || jobRole.getName().isEmpty()) {
            return "Name cannot be null or empty.";
        }

        if (jobRole.getName().length() > 50) {
            return "Name exceeds limit of 50 characters.";
        }

        if (jobRole.getSpecification().length() > 250) {
            return "Specification exceeds limit of 250 characters.";
        }

        if (!capabilityIds.contains(jobRole.getCapabilityId())) {
            System.out.println(capabilityIds);
            return "CapabilityID doesn't exist.";
        }

        if (!bandIds.contains(jobRole.getBandId())) {
            System.out.println(bandIds);
            return "BandID doesn't exist.";
        }

        return null;

    }

}