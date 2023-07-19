package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToGetCapabilityException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.model.Capability;
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

    public List<JobRole> getAllJobRoles() throws FailedToGetJobRolesException, SQLException {
        return jobRoleDao.getAllJobRoles(databaseConnector.getConnection());
    }

    public List<Capability> getCapability() throws FailedToGetCapabilityException {
        try {
            List<Capability> capabilityList = jobRoleDao.getCapability();
            return capabilityList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetCapabilityException();
        }
    }
}