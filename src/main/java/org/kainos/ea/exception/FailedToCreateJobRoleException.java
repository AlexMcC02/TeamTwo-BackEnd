package org.kainos.ea.exception;

public class FailedToCreateJobRoleException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to create job role.";
    }
}
