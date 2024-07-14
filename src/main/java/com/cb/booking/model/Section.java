package com.cb.booking.model;


import com.cb.booking.exception.BookingException;

import static com.cb.booking.constant.ApplicationConstant.BAD_REQUEST_ERROR_CODE;

public class Section {
    int bookingCursor= 0;
    boolean[] seats;

    Section(int noOfSeats) {
        seats = new boolean[noOfSeats];
    }

    public int bookSeat() {
        // normalize array index. index 0 --> seat 1
        return 1 + bookSeatInternal();
    }

    private int bookSeatInternal () {
        if (bookingCursor < seats.length) {
            seats[bookingCursor] = true;
            return bookingCursor++;
        } else {
            // check for a cancelled seat.
            for (int i = 0; i<seats.length; i++) {
                if (seats[i] == false) {
                    seats[i] = true;
                    return i;
                }
            }
        }
        throw new BookingException(BAD_REQUEST_ERROR_CODE, "Not enough seats");
    }

    public void unbookSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            throw new BookingException(BAD_REQUEST_ERROR_CODE, "Could not remove seat");
        }
        unbookSeatInternal(seatNumber-1);

    }

    private void unbookSeatInternal(int seatIndex) {
        boolean isBooked = seats[seatIndex];
        if (!isBooked) {
            throw new BookingException(BAD_REQUEST_ERROR_CODE, "Seat already unbooked");
        }
        seats[seatIndex] = false;
    }
}
