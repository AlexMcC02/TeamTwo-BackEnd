package org.kainos.ea.validator;

import org.kainos.ea.model.Band;

public class BandValidator {
    public String isValidBand(Band band) {
        if (band.getName().length() > 50) {
            return "Name greater than 50 characters";
        }
        return null;
    }
}
