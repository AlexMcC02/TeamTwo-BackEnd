package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.FailedToGetValidJobId;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleSpec;
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

    public JobRoleSpec getSpecificationById(int id) throws FailedToGetValidJobId {
        if (id < 0) {
            throw new FailedToGetValidJobId();
        }

        try {
            JobRoleSpec Spec = jobRoleDao.getSpecificationById(id);
            if (Spec == null) {
                throw new FailedToGetValidJobId();
            }
            return Spec;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetValidJobId();
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }
    }

}
