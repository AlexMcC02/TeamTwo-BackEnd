package org.kainos.ea.dao;

import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Capability> getAllCapabilities(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT ID, Name FROM `Capability`;");

        List<Capability> capabilityList = new ArrayList<>();

        while (rs.next()) {
            Capability capability = new Capability (
                    rs.getInt("ID"),
                    rs.getString("Name")
            );
            capabilityList.add(capability);
        }

        return capabilityList;

    }
}
