package com.devluis.rubberstore.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundProductException extends  Exception{
    private String message;
    private String detail;

    public NotFoundProductException(String detail, String message) {
        super(message);
        this.message = message;
        this.detail=detail;
    }

}
