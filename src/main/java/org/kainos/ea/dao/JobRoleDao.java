package org.kainos.ea.dao;

import org.kainos.ea.model.JobRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {

    public List<JobRole> getAllJobRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT ID, Name, Specification FROM `JobRole`;");

        List<JobRole> jobRoleList = new ArrayList<>();

        while (rs.next()) {
            JobRole jobRole = new JobRole (
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Specification")
            );
            jobRoleList.add(jobRole);
        }

        return jobRoleList;

    }

    public void deleteJobRole(Connection c, int jobId) throws SQLException {
        String deleteQuery = "DELETE FROM `JobRole` WHERE ID = ?";
        try (PreparedStatement pst = c.prepareStatement(deleteQuery)) {
            pst.setInt(1, jobId);
            pst.executeUpdate();
        }
    }
}
