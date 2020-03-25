package com.github.AbouOpenSource.Exception;

public class ExceptionRequest extends Exception {
    String request;
    String ERROR_MESSAGE;

    public ExceptionRequest(String request) {
        this.request = request;
    }

    public ExceptionRequest(String request, String ERROR_MESSAGE) {
        this.request = request;
        this.ERROR_MESSAGE = ERROR_MESSAGE;
    }

    @Override
    public String toString() {
        return "Request exception " +request;
    }

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}
