package com.strikes.crudDtoDemo.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException  (String message){
        super(message);
    }
}
