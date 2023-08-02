package org.kainos.ea.exception;

public class FailedToFindExistingIdInDb extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to find existing ID in the database.";
    }
}