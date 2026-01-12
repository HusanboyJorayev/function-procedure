package org.example.functionprosedure.exceptions;


import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
