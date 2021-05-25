package com.revature.project1.exception;

public class EmailUnavailableException extends RuntimeException{
    public EmailUnavailableException(){
        super("this email is already registered");
    }
}
