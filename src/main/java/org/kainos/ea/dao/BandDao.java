package org.kainos.ea.dao;

import org.kainos.ea.model.Band;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BandDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Band> getAllBands(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT ID, Name FROM `BandLevel`;");

        List<Band> bandList = new ArrayList<>();

        while (rs.next()) {
            Band band = new Band (
                    rs.getInt("ID"),
                    rs.getString("Name")
            );
            bandList.add(band);
        }
        return bandList;
    }
}