package org.kainos.ea.dao;

import org.kainos.ea.model.BandLevel;
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

    public List<BandLevel> getAllBandLevels(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT `JobRole`.id as 'id', `JobRole`.Name as 'Role Name', `BandLevel`.Name " +
                "as 'Band Level' FROM `BandLevel` JOIN `JobRole` ON `BandLevel`.ID = `JobRole`.CapabilityID;");

        List<BandLevel> bandLevelList = new ArrayList<>();

        while (rs.next()) {
            BandLevel bandLevel = new BandLevel(
                    rs.getInt("id"),
                    rs.getString("Role Name"),
                    rs.getString("Band Level")
            );
            bandLevelList.add(bandLevel);
        }
        return bandLevelList;
    }

}
