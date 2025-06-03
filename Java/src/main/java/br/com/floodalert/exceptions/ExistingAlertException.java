package br.com.floodalert.exceptions;

public class ExistingAlertException extends RuntimeException {

    public ExistingAlertException(String message) {
        super(message);
    }
}
