package org.kainos.ea.model;

public class PureJobRole {
    private int id;
    private String name;
    private String specification;
    private int bandId;
    private int capabilityId;
    private String urlLink;

    public PureJobRole(int id, String name, String specification, int bandId, int capabilityId, String urlLink) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.bandId = bandId;
        this.capabilityId = capabilityId;
        this.urlLink = urlLink;
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

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(int capabilityId) {
        this.capabilityId = capabilityId;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }
}
