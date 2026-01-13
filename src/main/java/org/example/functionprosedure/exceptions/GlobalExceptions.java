package org.example.functionprosedure.exceptions;

import lombok.RequiredArgsConstructor;
import org.example.functionprosedure.dto.GenericResponse;
import org.example.functionprosedure.dto.ValidDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptions {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public GenericResponse userAlreadyExistsException(final UserAlreadyExistsException e) {
        return GenericResponse.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    public GenericResponse customException(final CustomException e) {
        return GenericResponse.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse methodArgumentNotValidException(final MethodArgumentNotValidException e) {
        List<ValidDto> errorList = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorList.add(new ValidDto(error.getField(), error.getDefaultMessage()));
        });

        return GenericResponse.error(HttpStatus.BAD_REQUEST, errorList);

    }

    @ExceptionHandler(Exception.class)
    public GenericResponse handle(final Exception e) {
        return GenericResponse.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public GenericResponse handle(final HttpClientErrorException e) {
        HttpStatusCode statusCode = e.getStatusCode();
        if (statusCode instanceof HttpStatus status) {
            return GenericResponse.error(status, e.getMessage());
        }
        return GenericResponse.error(e.getStatusCode(), e.getMessage());
    }
}
