package org.kainos.ea.dao;

import org.kainos.ea.model.JobRole;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.model.JobRoleSpec;
import org.kainos.ea.model.PureJobRole;
import org.kainos.ea.util.DatabaseConnector;

import javax.ws.rs.HEAD;

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

    public int createJobRole(JobRoleRequest jobRole) throws SQLException, DatabaseConnectionException {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO `JobRole` (`Name`, `Specification`, `BandID`, `CapabilityID`, `UrlLink`) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, jobRole.getName());
        st.setString(2, jobRole.getSpecification());
        st.setInt(3, jobRole.getBandId());
        st.setInt(4, jobRole.getCapabilityId());
        st.setString(5, jobRole.getUrlLink());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public void deleteJobRole(Connection c, int jobId) throws SQLException {
        String deleteQuery = "DELETE FROM `JobRole` WHERE ID = ?";
        try (PreparedStatement pst = c.prepareStatement(deleteQuery)) {
            pst.setInt(1, jobId);
            pst.executeUpdate();
        }
    }

    public JobRoleSpec getSpecificationById ( int id, Connection c) throws SQLException, DatabaseConnectionException {

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT ID, Name, Specification, UrlLink "
                + " FROM JobRole WHERE ID =" + id + ";");
        while (rs.next()) {
            return new JobRoleSpec(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Specification"),
                    rs.getString("UrlLink")
            );
        }
        return null;
    }

    public PureJobRole getJobRoleById(int id, Connection c) throws SQLException {
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT ID, Name, Specification, BandID, CapabilityID, UrlLink FROM `JobRole` WHERE ID = " + id);

        while (rs.next()) {
            return new PureJobRole (
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Specification"),
                    rs.getInt("BandID"),
                    rs.getInt("CapabilityID"),
                    rs.getString("UrlLink")
            );

        }
        return null;
    }

}
