package com.cb.booking.exception;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookingException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public BookingException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
