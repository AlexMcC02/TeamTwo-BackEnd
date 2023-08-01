package org.kainos.ea.model;

public class JobRole {
    private int id;
    private String name;
    private String specification;
    private String bandLevel;
    private String capability;

    public JobRole(int id, String name, String specification, String bandLevel, String capability) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.bandLevel = bandLevel;
        this.capability = capability;
    }

    public JobRole() {}

    public JobRole(int id, String name, String specification, String capability) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.capability = capability;
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

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }
}