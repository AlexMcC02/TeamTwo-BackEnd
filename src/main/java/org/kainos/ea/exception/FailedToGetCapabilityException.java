package org.kainos.ea.exception;

public class FailedToGetCapabilityException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get capability.";
    }
}
