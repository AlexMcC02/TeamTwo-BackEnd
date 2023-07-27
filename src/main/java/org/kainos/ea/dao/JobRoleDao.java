package org.kainos.ea.dao;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleSpec;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();


    public List<JobRole> getAllJobRoles(Connection c) throws SQLException {

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM `JobRole`;");

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


    public JobRoleSpec getSpecificationById(int id) throws SQLException, DatabaseConnectionException {
        Connection c = databaseConnector.getConnection();
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

}
