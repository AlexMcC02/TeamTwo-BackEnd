package org.kainos.ea.dao;

import org.kainos.ea.model.JobRole;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();


    public List<JobRole> getAllJobRoles() throws SQLException {
        Connection c =databaseConnector.getConnection();
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


    public JobRole getSpecificationById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT ID, Name, Specification, BandID, CapabilityID"
                + " FROM JobRole WHERE ID =" + id + ";");
        while (rs.next()) {
            return new JobRole(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Specification"),
                    rs.getInt("BandID"),
                    rs.getInt("CapabilityID")
            );
        }
        return null;
    }

}
