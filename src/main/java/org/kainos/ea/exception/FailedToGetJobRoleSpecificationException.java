package org.kainos.ea.exception;

public class FailedToGetJobRoleSpecificationException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed get job role specifications.";
    }
}
