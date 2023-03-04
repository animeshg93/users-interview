package com.example.users.controller;

import com.example.users.exception.DuplicateException;
import com.example.users.exception.UserNotFoundException;
import com.example.users.model.User;
import com.example.users.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;
    @Mock
    UserRepository userRepository;
    User user;

    @BeforeEach
    public void init(){
        user = new User();
        user.setUserName("username");
        user.setAge(19);
        user.setFirstName("John");
        user.setLastName("Lobo");
        user.setId("id");
        user.setCountry("USA");
    }

    @Test
    public void testCreateUserSuccess(){
        when(userRepository.findUserByName(anyString())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(null);
        ResponseEntity<Object> response =  userController.createUser(user);
        Assertions.assertEquals("200 OK", response.getStatusCode().toString());
    }

    @Test
    public void testCreateUserException(){
        when(userRepository.findUserByName(anyString())).thenReturn(user);
        Assertions.assertThrows(DuplicateException.class, () -> userController.createUser(user));
    }

    @Test
    public void testGetUserSuccess(){
        when(userRepository.findUserByName(anyString())).thenReturn(user);
        ResponseEntity<Object> response =  userController.getUser("username");
        Assertions.assertEquals("200 OK", response.getStatusCode().toString());
    }

    @Test
    public void testGetUserException(){
        when(userRepository.findUserByName(anyString())).thenReturn(null);
        Assertions.assertThrows(UserNotFoundException.class, () -> userController.getUser("username"));
    }

    @Test
    public void testGetAll(){
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        ResponseEntity<Object> response =  userController.getAll();
        Assertions.assertEquals("200 OK", response.getStatusCode().toString());
    }

    @Test
    public void testUpdateUserSuccess(){
        when(userRepository.findUserByName(anyString())).thenReturn(user);
        doNothing().when(userRepository).delete(any());
        when(userRepository.save(any())).thenReturn(null);
        ResponseEntity<Object> response =  userController.updateWithUserName(user);
        Assertions.assertEquals("200 OK", response.getStatusCode().toString());
    }

    @Test
    public void testUpdateUserException(){
        when(userRepository.findUserByName(anyString())).thenReturn(null);
        Assertions.assertThrows(UserNotFoundException.class, () -> userController.updateWithUserName(user));
    }

}
