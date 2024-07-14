package com.cb.booking.controller;


import com.cb.booking.dto.PurchaseTicketRequest;
import com.cb.booking.dto.TicketModificationRequest;
import com.cb.booking.model.Ticket;
import com.cb.booking.model.User;
import com.cb.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/tickets/purchase")
    public ResponseEntity<Ticket> purchaseTicket(@Valid @RequestBody PurchaseTicketRequest purchaseTicketRequest, @RequestParam String section) {
        return new ResponseEntity<>(bookingService.purchaseTicket(purchaseTicketRequest, section), HttpStatus.CREATED);
    }

    @GetMapping("/tickets/{ticketNumber}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int ticketNumber) {

        return new ResponseEntity<>(bookingService.getTicketByNumber(ticketNumber), HttpStatus.OK);
    }

    @GetMapping("/tickets/section/{sectionName}")
    public ResponseEntity<List<Ticket>> getTicketBySection(@PathVariable("sectionName") String sectionName) {
        return new ResponseEntity<>(bookingService.getTicketsBySection(sectionName), HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<Ticket> getTicketByUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        User user = new User(firstName, lastName, email);
        return new ResponseEntity<>(bookingService.getTicket(user), HttpStatus.OK);
    }

    @DeleteMapping("/tickets/{ticketNumber}")
    public ResponseEntity removeTicket(@PathVariable("ticketNumber") int ticketNumber) {
        bookingService.removeTicket(ticketNumber);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/tickets/{ticketNumber}/seat")
    public ResponseEntity<Ticket> modifySeat(@PathVariable("ticketNumber") int ticketNumber, @RequestBody TicketModificationRequest ticketModificationRequest) {
        return new ResponseEntity<>(bookingService.modifySeat(ticketNumber, ticketModificationRequest), HttpStatus.OK);
    }
}
