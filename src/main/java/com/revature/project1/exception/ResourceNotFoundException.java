package com.revature.project1.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("ResourceNotFound");
    }
}
