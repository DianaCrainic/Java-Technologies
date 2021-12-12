package com.uaic.lab7.exceptions;

public class InvalidTimeFrameException extends RuntimeException {
    public InvalidTimeFrameException() {
        super("Invalid time frame");
    }
}
