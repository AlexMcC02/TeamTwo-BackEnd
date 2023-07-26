package org.kainos.ea.exception;

public class FailedToUpdateJobRoleException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to update JobRole.";
    }
}
