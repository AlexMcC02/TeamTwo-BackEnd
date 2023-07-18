package org.kainos.ea.model;

public class JobRole {
    private int id;
    private String name;
    private String specification;
    private int BandID;
    private int CapabilityID;

    public JobRole(int id, String name, String specification, int bandID, int capabilityID) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        BandID = bandID;
        CapabilityID = capabilityID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getBandID() {
        return BandID;
    }

    public void setBandID(int bandID) {
        BandID = bandID;
    }

    public int getCapabilityID() {
        return CapabilityID;
    }

    public void setCapabilityID(int capabilityID) {
        CapabilityID = capabilityID;
    }
}


