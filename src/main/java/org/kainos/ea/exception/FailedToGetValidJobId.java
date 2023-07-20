package org.kainos.ea.exception;

public class FailedToGetValidJobId extends Throwable {
    @Override
    public String getMessage() {
        return "Failed get job role id.";
    }
}
