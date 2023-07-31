package org.kainos.ea.exception;

public class FailedToDeleteJobRoleException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to delete job role.";
    }
}
