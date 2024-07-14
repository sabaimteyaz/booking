package com.cb.booking.service;

import com.cb.booking.dto.PurchaseTicketRequest;
import com.cb.booking.dto.TicketModificationRequest;
import com.cb.booking.model.Ticket;
import com.cb.booking.model.User;

import java.util.List;

public interface BookingService {

    Ticket purchaseTicket(PurchaseTicketRequest purchaseTicketRequest, String sectionName);
    Ticket getTicketByNumber(int ticketNumber);
    Ticket getTicket(User user);
    List<Ticket> getTicketsBySection(String sectionName);
    Ticket removeTicket(int ticketNumber);
    Ticket modifySeat(int ticketNumber, TicketModificationRequest ticketModificationRequest);
}
