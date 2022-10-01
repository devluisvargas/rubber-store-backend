package com.devluis.rubberstore.config;

import com.devluis.rubberstore.dto.Message;
import com.devluis.rubberstore.utils.NotFoundProductException;
import com.devluis.rubberstore.utils.ProductException;
import com.devluis.rubberstore.utils.ProductImgException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.time.LocalDate;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ProductException.class})
    public ResponseEntity<Message> handleProductException(ProductException exception){
        return error(HttpStatus.CONFLICT, exception.getMessage(), exception.getDetail());
    }

    @ExceptionHandler({ProductImgException.class})
    public ResponseEntity<Message> handleProductImgException(ProductImgException exception){
        return error(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getDetail());
    }

    @ExceptionHandler({NotFoundProductException.class})
    public ResponseEntity<Message> handleNotFoundProduct(NotFoundProductException exception){
        return error(HttpStatus.NOT_FOUND, exception.getMessage(), exception.getDetail());
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<Message> handleIOException(IOException exception){
        return error(HttpStatus.CONFLICT, exception.getMessage(), String.valueOf(exception.getCause()));
    }

    private ResponseEntity<Message> error(HttpStatus status, String message, String details){
        Message messageResponse = new Message(message, details, LocalDate.now());
        return ResponseEntity.status(status).body(messageResponse);
    }
}
