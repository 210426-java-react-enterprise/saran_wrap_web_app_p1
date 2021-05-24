package com.revature.project0.exception;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message){
        super(message);
    }

    public static class ResourcePersistenceException extends RuntimeException{

            public ResourcePersistenceException(String message){
                super(message);
            }
        }
}