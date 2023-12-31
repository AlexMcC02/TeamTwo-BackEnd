package org.kainos.ea.service;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToFindExistingIdInDb;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.exception.FailedToGetValidJobId;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleSpec;
import org.kainos.ea.util.DatabaseConnector;
import org.kainos.ea.validator.JobRoleValidator;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    public JobRoleDao jobRoleDao;
    public DatabaseConnector databaseConnector;
    public JobRoleValidator jobRoleValidator;

    public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;
        jobRoleValidator = new JobRoleValidator();
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

    public JobRoleSpec getSpecificationById(int id) throws FailedToGetValidJobId, FailedToFindExistingIdInDb, DatabaseConnectionException {
        if (id < 0) {
            throw new FailedToGetValidJobId();
        }

        try {
            JobRoleSpec Spec = jobRoleDao.getSpecificationById(id, databaseConnector.getConnection());
            if (Spec == null) {
                throw new FailedToFindExistingIdInDb();
            }
            return Spec;
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new DatabaseConnectionException();
        }
    }
}
