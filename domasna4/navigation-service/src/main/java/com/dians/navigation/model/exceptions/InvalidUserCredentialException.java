package com.dians.navigation.model.exceptions;

public class InvalidUserCredentialException extends RuntimeException {

    public InvalidUserCredentialException() {
        super("Invalid user credentials exception");
    }
}
