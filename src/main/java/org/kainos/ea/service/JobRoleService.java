package org.kainos.ea.service;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.FailedToGetJobRolesException;
import org.kainos.ea.model.JobRole;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDao jobRoleDao = new JobRoleDao();

    public List<JobRole> getAllJobRoles() throws FailedToGetJobRolesException {
        try {
            List<JobRole> jobRoleList = jobRoleDao.getAllJobRoles();
            return jobRoleList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRolesException();
        }
    }

    public JobRole getSpecificationById(int id) throws FailedToGetJobRolesException{
        try {
            JobRole Spec = jobRoleDao.getSpecificationById(id);
            return Spec;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRolesException();
        }
    }

}
