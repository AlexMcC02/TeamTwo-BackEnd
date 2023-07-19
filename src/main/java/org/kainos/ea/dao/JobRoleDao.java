package org.kainos.ea.dao;

import org.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {

    public List<JobRole> getAllJobRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM `JobRole`;");

        List<JobRole> jobRoleList = new ArrayList<>();

        while (rs.next()) {
            JobRole jobRole = new JobRole (
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Specification"),
                    rs.getInt("BandID"),
                    rs.getInt("CapabilityID")
            );
            jobRoleList.add(jobRole);
        }

        return jobRoleList;

    }

}
