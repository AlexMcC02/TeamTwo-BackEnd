package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.model.JobRole;
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
