package com.example.users.exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String userName){
        super(String.format("A user with username '%s' already exists", userName));
    }
}
