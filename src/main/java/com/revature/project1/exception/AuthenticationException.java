package com.revature.project1.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Could not authenticaUser with the provided credentials");
    }
    public AuthenticationException(String message) {
        super(message);
    }


}