package com.cb.booking.dto;

import com.cb.booking.model.User;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class PurchaseTicketRequest {
    private String from;
    private String to;
    private User user;
    private double pricePaid;
}
