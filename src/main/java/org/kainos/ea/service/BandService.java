package org.kainos.ea.service;

import org.kainos.ea.dao.BandDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetBandsException;
import org.kainos.ea.model.Band;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class BandService {

    public BandDao bandDao;

    public DatabaseConnector databaseConnector;

    public BandService(BandDao bandDao, DatabaseConnector databaseConnector) {
        this.bandDao = bandDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Band> getAllBands() throws FailedToGetBandsException {
        try {
            return bandDao.getAllBands(databaseConnector.getConnection());
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetBandsException();
        }
    }

}