package org.kainos.ea.exception;

public class JobRoleDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return "Job role doesn't exist.";
    }
}
