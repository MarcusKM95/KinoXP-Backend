package com.example.kinoxp.exceptions;

import com.example.kinoxp.Response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ReservationException.class)
        public ResponseEntity<ResponseMessage> handleReservationException(ReservationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseMessage("Reservation failed: " + e.getMessage()));
        }
    }


