package org.kainos.ea.service;

import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.exception.FailedToGetBandsException;
import org.kainos.ea.exception.FailedToGetCapabilitiesException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.Capability;
import org.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class CapabilityService {

    public CapabilityDao capabilityDao;

    public DatabaseConnector databaseConnector;

    public CapabilityService(CapabilityDao capabilityDao, DatabaseConnector databaseConnector) {
        this.capabilityDao = capabilityDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Capability> getAllCapabilities() throws FailedToGetCapabilitiesException {
        try {
            return capabilityDao.getAllCapabilities(databaseConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetCapabilitiesException();
        }
    }
}
