package org.kainos.ea.service;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleSpec;
import org.kainos.ea.model.PureJobRole;
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

    public PureJobRole getJobRoleById(int id) throws JobRoleDoesNotExistException, FailedToGetJobRolesException {
        try {
            PureJobRole jobRole = jobRoleDao.getJobRoleById(id, databaseConnector.getConnection());
            if (jobRole == null) {
                throw new JobRoleDoesNotExistException();
            }
            return jobRole;
        } catch(SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRolesException();
        }
    }

    public void updateJobRole(int id, JobRoleRequest jobRole) throws JobRoleDoesNotExistException, FailedToUpdateJobRoleException, InvalidJobRoleException {
        try {

            String validation = jobRoleValidator.isValidJobRole(jobRole);

            if (validation != null) {
                throw new InvalidJobRoleException(validation);
            }

            PureJobRole jobRoleToUpdate = jobRoleDao.getJobRoleById(id, databaseConnector.getConnection());

            if (jobRoleToUpdate == null) {
                throw new JobRoleDoesNotExistException();
            }

            jobRoleDao.updateJobRole(id, jobRole, databaseConnector.getConnection());
        } catch(SQLException | DatabaseConnectionException e) {
            System.err.println(e);
            throw new FailedToUpdateJobRoleException();
        }
    }
}
