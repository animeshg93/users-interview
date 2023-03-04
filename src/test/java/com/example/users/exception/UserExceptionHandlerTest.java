package com.example.users.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserExceptionHandlerTest {

    UserExceptionHandler handler;

    @BeforeEach
    public void init(){
        handler = new UserExceptionHandler();
    }

    @Test
    public void testDuplicateExceptionHandler(){
        ResponseEntity<Object> entity = handler.handleDuplicateIdException(new DuplicateException("username"));
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUserNotFoundExceptionHandler(){
        ResponseEntity<Object> entity = handler.handleUserNotFoundException(new UserNotFoundException("username"));
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
