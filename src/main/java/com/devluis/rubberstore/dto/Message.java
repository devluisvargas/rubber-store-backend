package com.devluis.rubberstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Message {
    private String message;
    private String detail;
    private LocalDate dateTime;
}
