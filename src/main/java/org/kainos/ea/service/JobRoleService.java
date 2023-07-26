package org.kainos.ea.service;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.util.DatabaseConnector;
import org.kainos.ea.validator.JobRoleValidator;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    public JobRoleDao jobRoleDao;
    public DatabaseConnector databaseConnector;
    private JobRoleValidator jobRoleValidator = new JobRoleValidator();

    public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobRole> getAllJobRoles() throws FailedToGetJobRolesException {
        try {
            return jobRoleDao.getAllJobRoles(databaseConnector.getConnection());
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRolesException();
        }
    }

    public int createJobRole(JobRoleRequest jobRole) throws FailedToCreateJobRoleException, InvalidJobRoleException {
        try {

            String validation = jobRoleValidator.isValidJobRole(jobRole);

            if (validation != null) {
                throw new InvalidJobRoleException(validation);
            }

            int id = jobRoleDao.createJobRole(jobRole);

            if (id == -1) {
                throw new FailedToCreateJobRoleException();
            }

            return id;
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateJobRoleException();
        }
    }

}
