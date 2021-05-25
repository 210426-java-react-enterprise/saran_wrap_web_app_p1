package com.revature.project1.exception;

public class DataSourceException extends RuntimeException{
    public DataSourceException(){
        super("There was a problem when communicating with the database");
    }
    public DataSourceException(String message) {
        super(message);
    }
}