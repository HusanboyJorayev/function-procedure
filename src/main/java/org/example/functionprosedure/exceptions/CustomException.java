package org.example.functionprosedure.exceptions;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
