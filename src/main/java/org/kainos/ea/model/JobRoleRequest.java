package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {
    private String name;
    private String specification;
    private int bandId;
    private int capabilityId;

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

    @JsonCreator
    public JobRoleRequest(
            @JsonProperty("name") String name,
            @JsonProperty("specification") String specification,
            @JsonProperty("bandId") int bandId,
            @JsonProperty("capabilityId")int capabilityId){
        this.name = name;
        this.specification = specification;
        this.bandId = bandId;
        this.capabilityId = capabilityId;
    }
}