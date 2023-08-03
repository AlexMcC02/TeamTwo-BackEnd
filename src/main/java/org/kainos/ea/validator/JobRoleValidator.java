package org.kainos.ea.validator;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;

public class JobRoleValidator {
    private DatabaseConnector databaseConnector;
    private JobRoleDao jobRoleDao;

    private ArrayList<Integer> capabilityIds;
    private ArrayList<Integer> bandIds;

    public JobRoleValidator(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;

        {
            try {
                capabilityIds = jobRoleDao.getAllCapabilityIds(databaseConnector.getConnection());
                bandIds = jobRoleDao.getAllBandIds(databaseConnector.getConnection());
            } catch (SQLException | DatabaseConnectionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String isValidJobRole(JobRoleRequest jobRole) {
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
            return "CapabilityID doesn't exist.";
        }

        if (!bandIds.contains(jobRole.getBandId())) {
            return "BandID doesn't exist.";
        }

        return null;

    }

}