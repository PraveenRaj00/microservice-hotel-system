package com.example.RatingService.exceptions;

import org.aspectj.apache.bcel.classfile.annotation.RuntimeInvisTypeAnnos;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }

    public ResourceNotFoundException(){
        super("Resource not available !! ");
    }
}
