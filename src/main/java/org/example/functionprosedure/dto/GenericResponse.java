package org.example.functionprosedure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse implements Serializable {

    private String message;
    private Integer code;
    private HttpStatus status;
    private Object data;

    public static GenericResponse success(Object data) {
        return GenericResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message("OK")
                .data(data)
                .build();
    }

    public static GenericResponse success(List<?> data) {
        return GenericResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message("OK")
                .data(data)
                .build();
    }

    public static GenericResponse success(HttpStatus status) {
        return GenericResponse.builder()
                .code(status.value())
                .status(status)
                .message("OK")
                .build();
    }

    public static GenericResponse error(HttpStatus status) {
        return GenericResponse.builder()
                .code(status.value())
                .status(status)
                .message(status.getReasonPhrase())
                .build();
    }

    public static GenericResponse error(HttpStatus status, String message) {
        return GenericResponse.builder()
                .code(status.value())
                .status(status)
                .message(message)
                .build();
    }

    public static GenericResponse error(HttpStatus status, List<?> data) {
        return GenericResponse.builder()
                .code(status.value())
                .status(status)
                .data(data)
                .build();
    }

    public static GenericResponse error(HttpStatusCode code, String message) {
        return GenericResponse.builder()
                .code(code.value())
                .message(message)
                .build();
    }
}
