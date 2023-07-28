package org.kainos.ea.exception;

public class DatabaseConnectionException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to connect to database.";
    }

}
