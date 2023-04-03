package com.hilltop.service;

import com.hilltop.exception.BookingServiceException;
import com.hilltop.model.Booking;
import com.hilltop.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * BookingService
 */
@Service
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void saveBooking(Booking booking) {
        try {
            bookingRepository.save(booking);
        } catch (DataAccessException e) {
            log.error("Error saving booking details due to : {}", e.getMessage());
            throw new BookingServiceException("Error saving booking details." + e);
        }
    }
}
