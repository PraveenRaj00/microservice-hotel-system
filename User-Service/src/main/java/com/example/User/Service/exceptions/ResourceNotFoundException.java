package com.example.User.Service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("Resource not available");
    }
    public ResourceNotFoundException(String e) {
        super(e);
    }
}
