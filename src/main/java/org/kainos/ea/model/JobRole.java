package org.kainos.ea.model;

public class JobRole {
    private int id;
    private String name;
    private String specification;

    public JobRole(int id, String name, String specification) {
        this.id = id;
        this.name = name;
        this.specification = specification;
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

}


