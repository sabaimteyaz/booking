package com.cb.booking.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
    private int ticketNumber;
    private String from;
    private String to;
    private User user;
    private double price;
    private String seat;
}
