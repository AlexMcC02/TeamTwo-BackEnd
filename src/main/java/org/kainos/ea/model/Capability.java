package org.kainos.ea.model;

public class Capability {
    private String roleName;
    private String capability;
    public Capability(String roleName, String capability) {
        this.roleName = roleName;
        this.capability = capability;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }
}