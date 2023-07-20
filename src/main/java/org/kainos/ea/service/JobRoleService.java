package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.exception.FailedToGetValidJobId;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDao jobRoleDao = new JobRoleDao();
    public DatabaseConnector databaseConnector;

    public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobRole> getAllJobRoles() throws FailedToGetJobRolesException {
        try {
            List<JobRole> jobRoleList = jobRoleDao.getAllJobRoles();
            return jobRoleList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRolesException();
        }
    }

    public JobRole getSpecificationById(int id) throws FailedToGetValidJobId {
        if (id < 0) {
            throw new FailedToGetValidJobId();
        }

        try {
            JobRole Spec = jobRoleDao.getSpecificationById(id);
            if (Spec == null) {
                throw new FailedToGetValidJobId();
            }
            return Spec;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetValidJobId();
        }
    }

}
