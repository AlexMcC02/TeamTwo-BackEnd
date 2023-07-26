package org.kainos.ea.dao;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.util.DatabaseConnector;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JobRoleDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();


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

    public int createJobRole(JobRoleRequest jobRole) throws SQLException, DatabaseConnectionException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO `JobRole` (`Name`, `Specification`, `BandID`, `CapabilityID`) VALUES (?, ?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, jobRole.getName());
        st.setString(2, jobRole.getSpecification());
        st.setInt(3, jobRole.getBandId());
        st.setInt(4, jobRole.getCapabilityId());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

}
