package org.kainos.ea.dao;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.JobRole;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.kainos.ea.model.PureJobRole;
import org.kainos.ea.util.DatabaseConnector;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.JobRoleSpec;
import java.util.Properties;

import javax.ws.rs.HEAD;

public class JobRoleDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();


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

    public JobRoleSpec getSpecificationById(int id, Connection c) throws SQLException, DatabaseConnectionException {

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

    public int createJobRole(JobRoleRequest jobRole) throws SQLException, DatabaseConnectionException {
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

    public void updateJobRole(int id, JobRoleRequest jobRole, Connection c) throws SQLException {

        String updateStatement = "UPDATE `JobRole` SET Name = ?, Specification = ?, BandID = ?, CapabilityID = ?, UrlLink = ? WHERE `JobRole`.ID = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, jobRole.getName());
        st.setString(2, jobRole.getSpecification());
        st.setInt(3, jobRole.getBandId());
        st.setInt(4, jobRole.getCapabilityId());
        st.setString(5, jobRole.getUrlLink());
        st.setInt(6, id);

        st.executeUpdate();

    }

}
