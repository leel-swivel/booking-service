package com.hilltop.exception;

/**
 * InvalidBookingException
 */
public class InvalidBookingException extends BookingServiceException {
    public InvalidBookingException(String errorMessage) {
        super(errorMessage);
    }
}
