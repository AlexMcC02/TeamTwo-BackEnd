package org.kainos.ea.model;

public class JobRoleSpec {
    private int id;
    private String name;
    private String specification;

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    private String urlLink;

    public JobRoleSpec(int id, String name, String specification, String urlLink) {
        this.id = id;
        this.name = name;
        this.specification = specification;
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

}