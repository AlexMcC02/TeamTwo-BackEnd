package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.FailedToUpdateJobRoleException;
import org.kainos.ea.exception.JobRoleDoesNotExistException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    public JobRoleDao jobRoleDao;
    public DatabaseConnector databaseConnector;

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

    public JobRoleRequest getJobRoleById(int id) throws JobRoleDoesNotExistException, FailedToGetJobRolesException {
        try {
            JobRoleRequest jobRole = jobRoleDao.getJobRoleById(id, databaseConnector.getConnection());
            if (jobRole == null) {
                throw new JobRoleDoesNotExistException();
            }
            return jobRole;
        } catch(SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRolesException();
        }
    }

    public void updateJobRole(int id, JobRoleRequest jobRole) throws JobRoleDoesNotExistException, FailedToUpdateJobRoleException {
        try {
            JobRoleRequest jobRoleToUpdate = jobRoleDao.getJobRoleById(id, databaseConnector.getConnection());

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
