package com.hilltop.exception;

/**
 * BookingServiceException
 */
public class BookingServiceException extends RuntimeException {

    /**
     * BaseComponentException Exception with error message.
     *
     * @param errorMessage error message
     */
    public BookingServiceException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Authentication Exception with error message and throwable error
     *
     * @param errorMessage error message
     * @param error        error
     */
    public BookingServiceException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

}