package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetBandLevelException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.model.BandLevel;
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
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRolesException();
        }
    }

    public List<BandLevel> getAllBandLevels() throws FailedToGetBandLevelException {
        try {
            return jobRoleDao.getAllBandLevels(databaseConnector.getConnection());
        } catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToGetBandLevelException();
        }
    }

}
