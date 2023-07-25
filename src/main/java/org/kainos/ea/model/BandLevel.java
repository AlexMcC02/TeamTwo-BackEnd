package org.kainos.ea.model;

public class BandLevel {
    private int id;
    private String roleName;
    private String bandLevel;

    public BandLevel(int id, String roleName, String bandLevel) {
        this.id = id;
        this.roleName = roleName;
        this.bandLevel = bandLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
