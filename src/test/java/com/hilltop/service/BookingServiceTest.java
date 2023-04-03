package com.hilltop.service;

import com.hilltop.domain.request.BookingRequestDto;
import com.hilltop.exception.BookingServiceException;
import com.hilltop.model.Booking;
import com.hilltop.repository.BookingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BookingServiceTest {

    private BookingService bookingService;
    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bookingService = new BookingService(bookingRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void Should_SaveABooking() {
        Booking sampleBooking = getSampleBooking();
        bookingService.saveBooking(sampleBooking);
        verify(bookingRepository, times(1)).save(sampleBooking);
    }

    @Test
    void Should_ThrowException_When_SavingABooking(){
        Booking sampleBooking = getSampleBooking();
        when(bookingRepository.save(sampleBooking)).thenThrow(new DataAccessException("ERROR") {
        });
        BookingServiceException bookingServiceException =
                assertThrows(BookingServiceException.class, () -> bookingService.saveBooking(sampleBooking));
        assertEquals("Error saving booking details.",bookingServiceException.getMessage());
    }

    private BookingRequestDto getSampleBookingRequestDto() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto();
        bookingRequestDto.setCheckInDate(new Date(1680495637));
        bookingRequestDto.setCheckOutDate(new Date(1680495637));
        bookingRequestDto.setDueAmount(BigDecimal.valueOf(1220.00));
        bookingRequestDto.setFullAmount(BigDecimal.valueOf(5220.00));
        return bookingRequestDto;
    }

    private Booking getSampleBooking() {
        BookingRequestDto sampleBookingRequestDto = getSampleBookingRequestDto();
        return new Booking(sampleBookingRequestDto);
    }
}