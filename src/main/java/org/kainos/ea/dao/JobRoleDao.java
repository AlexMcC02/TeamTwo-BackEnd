package org.kainos.ea.dao;

import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {

    public List<JobRole> getAllJobRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT `JobRole`.ID, `JobRole`.Name, Specification, `BandLevel`.Name AS " +
                "'Band Level', `Capability`.Name AS 'Capability' FROM `JobRole` INNER JOIN `Capability` ON " +
                "`JobRole`.CapabilityID = `Capability`.ID INNER JOIN `BandLevel` ON `JobRole`.BandID = `BandLevel`.ID;");

        List<JobRole> jobRoleList = new ArrayList<>();

        while (rs.next()) {
            JobRole jobRole = new JobRole (
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Specification"),
                    rs.getString("Band Level"),
                    rs.getString("Capability")
            );
            jobRoleList.add(jobRole);
        }

        return jobRoleList;

    }

    public JobRoleRequest getJobRoleById(int id, Connection c) throws SQLException {
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT Name, Specification, BandID, CapabilityID FROM `JobRole` WHERE ID = " + id);

        while (rs.next()) {
            return new JobRoleRequest (
                    rs.getString("Name"),
                    rs.getString("Specification"),
                    rs.getInt("BandID"),
                    rs.getInt("CapabilityID")
            );

        }
        return null;
    }

    public void updateJobRole(int id, JobRoleRequest jobRole, Connection c) throws SQLException {

        String updateStatement = "UPDATE `JobRole` SET Name = ?, Specification = ?, BandID = ?, CapabilityID = ? WHERE `JobRole`.ID = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, jobRole.getName());
        st.setString(2, jobRole.getSpecification());
        st.setInt(3, jobRole.getBandId());
        st.setInt(4, jobRole.getCapabilityId());
        st.setInt(5, id);

        st.executeUpdate();

    }

}
