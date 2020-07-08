package com.rooms.broadcast;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rooms.broadcast.Room.Exceptions.RoomNotFoundException;
import com.rooms.broadcast.User.Exceptions.UserAlreadyExistsException;
import com.rooms.broadcast.User.Exceptions.UserNotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class,RoomNotFoundException.class})
    public ResponseEntity<ErrorResponse> notFoundHandler(RuntimeException ex) {
        ErrorResponse errorResponse = new
                ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<ErrorResponse> userAlreadyExistsHandler(UserAlreadyExistsException ex){
        ErrorResponse errorResponse = new
                ErrorResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @Data
    private static class ErrorResponse{
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        private LocalDateTime timestamp;
        private int status;
        private String message;

        public ErrorResponse(LocalDateTime timestamp, int status, String message) {
            this.timestamp = timestamp;
            this.status = status;
            this.message = message;
        }
    }
}
