package com.cb.booking.service;

import com.cb.booking.dto.PurchaseTicketRequest;
import com.cb.booking.dto.TicketModificationRequest;
import com.cb.booking.exception.BookingException;
import com.cb.booking.model.Section;
import com.cb.booking.model.Ticket;
import com.cb.booking.model.Train;
import com.cb.booking.model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cb.booking.constant.ApplicationConstant.BAD_REQUEST_ERROR_CODE;
import static com.cb.booking.constant.ApplicationConstant.NOT_FOUND_ERROR_CODE;


@Service
public class BookingServiceImpl implements BookingService {
    public static final String[] sectionNames = {"A", "B"};
    public static final String FROM = "London";
    public static final String TO = "France";
    public static final double PRICE = 20.0;

    private int ticketNumber = 0;
    private Map<User, Ticket> userTicketMap = new HashMap<>();
    private Map<Integer, User> ticketMap = new HashMap<>();
    private Train train = new Train(sectionNames, FROM, TO, PRICE);

    @Override
    public Ticket purchaseTicket(PurchaseTicketRequest purchaseTicketRequest, String sectionName) {
        validateSection(sectionName);
        validateTicketDetails(purchaseTicketRequest);
        User user = purchaseTicketRequest.getUser();
        if(userTicketMap.containsKey(user)) {
            throw new BookingException(BAD_REQUEST_ERROR_CODE, "User already has a ticket");
        }

        return allocateUserTicket(user, sectionName);
    }

    @Override
    public Ticket getTicketByNumber(int ticketNumber) {
        if(!ticketMap.containsKey(ticketNumber)) {
            throw new BookingException(NOT_FOUND_ERROR_CODE, "Could not found ticket for given ticketNumber");
        }

        User user = ticketMap.get(ticketNumber);
        return userTicketMap.get(user);
    }

    @Override
    public Ticket getTicket(User user) {
        if(!userTicketMap.containsKey(user)) {
            throw new BookingException(NOT_FOUND_ERROR_CODE, "User ticket not found");
        }
        return userTicketMap.get(user);
    }

    @Override
    public List<Ticket> getTicketsBySection(String sectionName) {
        validateSection(sectionName);
        List<Ticket> sectionTicketList=  userTicketMap.values().stream().filter(ticket -> ticket.getSeat().substring(0,1).equalsIgnoreCase(sectionName))
                .collect(Collectors.toList());
        if(sectionTicketList.isEmpty()) {
            throw new BookingException(NOT_FOUND_ERROR_CODE, "No tickets found for the given section");
        }
        return sectionTicketList;
    }

    @Override
    public Ticket removeTicket(int ticketNumber) {
        //find user ticket from map
        if(!ticketMap.containsKey(ticketNumber)) {
            throw new BookingException(NOT_FOUND_ERROR_CODE, "Ticket does not exist");
        }
        User user = ticketMap.get(ticketNumber);
        cancelTicket(userTicketMap.get(user));
        ticketMap.remove(ticketNumber);
        return userTicketMap.remove(user);
    }

    @Override
    public Ticket modifySeat(int ticketNumber, TicketModificationRequest ticketModificationRequest) {
        validateSection(ticketModificationRequest.getSectionName());

        //TODO: Validations for the availability in the new section
        Ticket ticket = removeTicket(ticketNumber);

        return allocateUserTicket(ticket.getUser(), ticketModificationRequest.getSectionName());
    }

    private void cancelTicket(Ticket ticket) {
        String seat = ticket.getSeat();
        String sectionName = seat.substring(0,1);
        int seatNumber = Integer.parseInt(seat.substring(1,2));
        Section section = train.getTrainSectionsMap().get(sectionName);
        section.unbookSeat(seatNumber);
    }

    private void validateTicketDetails(PurchaseTicketRequest purchaseTicketRequest) {
        String errorMessage = "";
        if(!purchaseTicketRequest.getFrom().equalsIgnoreCase("London") ) {
            errorMessage += "From station is invalid | ";
        }
        if(!purchaseTicketRequest.getTo().equalsIgnoreCase("France") ) {
            errorMessage += "To station is invalid | ";
        }
        if(purchaseTicketRequest.getPricePaid() != 20.0) {
            errorMessage += "PricePaid is invalid | ";
        }

        if(!errorMessage.isBlank()) {
            //TODO throw exception with message
            throw new BookingException(BAD_REQUEST_ERROR_CODE, errorMessage.substring(0, errorMessage.lastIndexOf(" |")));
        }
    }

    private void validateSection(String sectionName) {
        if(!Arrays.stream(sectionNames).toList().contains(sectionName)) {
            throw new BookingException(BAD_REQUEST_ERROR_CODE, "Invalid section name");
        }
    }

    private Ticket allocateUserTicket(User user, String section) {
        String seat = allocateSeat(section);
        Ticket ticket = new Ticket(++ticketNumber, train.getFrom(), train.getTo(), user, train.getPrice(), seat);
        userTicketMap.put(user, ticket);
        ticketMap.put(ticketNumber, user);
        return ticket;
    }

    private String allocateSeat(String sectionName) {
        Section section = train.getTrainSectionsMap().get(sectionName);
        int seatNumber = section.bookSeat();
        return sectionName + seatNumber;
    }
}
