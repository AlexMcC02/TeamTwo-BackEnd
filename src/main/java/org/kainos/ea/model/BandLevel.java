package org.kainos.ea.model;

public class BandLevel {
    private String roleName;
    private String bandLevel;

    public BandLevel(String roleName, String bandLevel) {
        this.roleName = roleName;
        this.bandLevel = bandLevel;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }
}
