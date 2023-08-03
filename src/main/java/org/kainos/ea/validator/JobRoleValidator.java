package org.kainos.ea.validator;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;

public class JobRoleValidator {
    private DatabaseConnector databaseConnector;

//    public JobRoleValidator(DatabaseConnector databaseConnector) {
//        this.databaseConnector = databaseConnector;
//        JobRoleDao jobRoleDao = new JobRoleDao();
//        DatabaseConnector databaseConnector = new DatabaseConnector();
//        ArrayList<Integer> capabilityIds = jobRoleDao.getAllCapabilityIds(DatabaseConnector);
//        ArrayList<Integer> bandIds = new ArrayList();
//    }

    ArrayList<Integer> capabilityIds = jobRoleDao.getAllCapabilityIds(databaseConnector.getConnection());

    public JobRoleValidator() throws DatabaseConnectionException, SQLException {
    }


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

        if (capabilityIds) {}
         return null;
    }

}