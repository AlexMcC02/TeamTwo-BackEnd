package org.kainos.ea.dao;
import org.kainos.ea.model.Capability;
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

    public List<Capability> getCapability(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT `JobRole`.Name as 'Role Name', `Capability`.Name as 'Capability' FROM `Capability` " +
                "JOIN `JobRole` ON `Capability`.ID = `JobRole`.CapabilityID;");

        List<Capability> capabilityList = new ArrayList<>();

        while (rs.next()) {
            Capability capability = new Capability(
                    rs.getString("Role Name"),
                    rs.getString("Capability")
            );
            capabilityList.add(capability);
        }
        return capabilityList;
    }
}