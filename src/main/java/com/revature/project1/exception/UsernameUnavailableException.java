package com.revature.project1.exception;

public class UsernameUnavailableException extends RuntimeException{
    public UsernameUnavailableException() {
        super("username taken");
    }
}
