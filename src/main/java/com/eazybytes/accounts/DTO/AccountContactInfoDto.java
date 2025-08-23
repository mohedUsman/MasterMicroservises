package com.eazybytes.accounts.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * what is this record class?
 * A record class in Java is a special kind of class that is used to create immutable data
 * carriers. It is a feature introduced in Java 14 as a preview feature and became a
 * standard feature in Java 16. It has getters for all fields, but no setters,
 * and it automatically generates methods like equals(), hashCode(), and toString().
 * * The primary purpose of a record class is to hold data in a concise and readable way,
 * without the boilerplate code typically associated with regular classes.
 * we can also use DTO (Data Transfer Object) as a record class.
 */
//this annotation is used to bind the properties from the application.properties file with prifix "accounts" as we have given accounts in yml file
@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
/*
 why I am changed record to class?
 A record in Java is a special type of class that is intended to be a simple, immutable
 but as in spring config if I want to change the properties is run time this will not be possible as it is immutable
 so I have changed it to class and added @Getter and @Setter annotation from lombok to generate the getters and setters for the fields
 */
public class AccountContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
