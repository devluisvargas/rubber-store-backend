package com.devluis.rubberstore.utils;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductException extends Exception{
    private String message;
    private String detail;

    public ProductException(String detail, String message) {
        super(message);
        this.message = message;
        this.detail=detail;
    }
}
