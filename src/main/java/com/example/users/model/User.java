package com.example.users.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document
public class User {
    @Id
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private int age;
    //might change this to enum
    private String country;
}
