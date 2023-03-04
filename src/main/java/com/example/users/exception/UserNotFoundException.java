package com.example.users.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userName){
        super(String.format("Username %s not found", userName));
    }
}
