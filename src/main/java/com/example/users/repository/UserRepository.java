package com.example.users.repository;

import com.example.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{userName:'?0'}")
    User findUserByName(String userName);

    @Query(value="{firstName:'?0'}",fields="{'firstName' : 1, 'lastName': 1, 'userName':1, 'age' : 1, }")
    List<User> findAllByFirstName(String userName);
}
