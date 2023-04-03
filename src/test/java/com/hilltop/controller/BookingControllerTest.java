package com.hilltop.controller;

import com.hilltop.configuration.Translator;
import com.hilltop.domain.request.BookingRequestDto;
import com.hilltop.exception.BookingServiceException;
import com.hilltop.model.Booking;
import com.hilltop.service.BookingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookingControllerTest {

    private static final String CREATE_BOOKING_URL = "/api/v1/booking";

    @Mock
    private BookingService bookingService;
    @Mock
    private Translator translator;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        initMocks(this);
        BookingController bookingController = new BookingController(translator, bookingService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void Should_ReturnOk_When_CreatingAHotel() throws Exception {
        BookingRequestDto sampleBookingRequestDto = getSampleBookingRequestDto();
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_BOOKING_URL)
                        .content(sampleBookingRequestDto.toLogJson())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

@Test
    void Should_ReturnBookingServiceException_WhenCreatingABooking() throws Exception {
        BookingRequestDto sampleBookingRequestDto = getSampleBookingRequestDto();
        Booking sampleBooking = getSampleBooking();
        doThrow(new BookingServiceException("ERROR")).when(bookingService).saveBooking(any());

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_BOOKING_URL)
                        .content(sampleBookingRequestDto.toLogJson())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

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
        Booking booking = new Booking(sampleBookingRequestDto);
        return booking;
    }
}