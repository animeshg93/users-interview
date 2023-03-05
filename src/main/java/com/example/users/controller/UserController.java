package com.example.users.controller;

import com.example.users.exception.DuplicateException;
import com.example.users.exception.UserNotFoundException;
import com.example.users.model.User;
import com.example.users.repository.UserRepository;
import com.example.users.util.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        String userName = user.getUserName();
        if(userRepository.findUserByName(userName) != null){
            logger.error("Username {} has been taken.", userName);
            throw new DuplicateException(userName);
        }

        userRepository.save(user);
        
        return new ResponseEntity<>
                (ResponseHandler.successResponse(String.format("Username %s successfully created", userName)), HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<Object> getUser(@RequestParam(value = "userName") String userName){
        User user = userRepository.findUserByName(userName);
        if(user == null){
            logger.error("Username '{}' not found", userName);
            throw new UserNotFoundException(userName);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/getUsersByTitle")
    public ResponseEntity<Object> getUsersByTitle(@RequestParam(value = "title") String title){
        List<User> users = userRepository.findUsersByTitle(title);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){
        List<User> userList = userRepository.findAll();

        return new ResponseEntity<>
                (ResponseHandler.getAllResponse(userList), HttpStatus.OK);

    }

    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateWithUserName(@RequestBody User user){
        String userName = user.getUserName();
        deleteUser(userName);
        logger.debug("Adding new user with same username '{}'", userName);
        userRepository.save(user);

        return new ResponseEntity<>
                (ResponseHandler.successResponse("User successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Object> deleteUser(@RequestParam(value = "userName") String userName){
        User currentUser = userRepository.findUserByName(userName);
        if(currentUser == null){
            logger.error("Username '{}' not found", userName);
            throw new UserNotFoundException(userName);
        }

        logger.debug("Deleting user '{}", userName);
        userRepository.delete(currentUser);

        return new ResponseEntity<>
                (ResponseHandler.successResponse("User successfully deleted"), HttpStatus.OK);
    }
}
