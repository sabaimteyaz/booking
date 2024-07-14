package com.cb.booking.service;

import com.cb.booking.dto.PurchaseTicketRequest;
import com.cb.booking.model.Ticket;
import com.cb.booking.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookingApplicationTests {

	@InjectMocks
	BookingServiceImpl trainService;

	@Test
	void purchaseTicket() {

		Ticket ticket = trainService.purchaseTicket(getPurchaseTicketRequest(), getSection());
		assertEquals(ticket.getTicketNumber(), 1);
	}


	//Utility methods
	PurchaseTicketRequest getPurchaseTicketRequest() {
		PurchaseTicketRequest purchaseTicketRequest = new PurchaseTicketRequest();
		purchaseTicketRequest.setFrom("London");
		purchaseTicketRequest.setTo("France");
		purchaseTicketRequest.setUser(getUser());
		purchaseTicketRequest.setPricePaid(20.0);
		return purchaseTicketRequest;
	}

	User getUser() {
		return new User("Test", "User", "test@test.com");
	}

	String getSection() {
		return "A";
	}

}
