package br.com.floodalert.exceptions;

public class ExistingDangerAreaException extends RuntimeException{
    public ExistingDangerAreaException(String message) {
        super(message);
    }
}
