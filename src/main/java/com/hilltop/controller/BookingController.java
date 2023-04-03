package com.hilltop.controller;

import com.hilltop.configuration.Translator;
import com.hilltop.domain.request.BookingRequestDto;
import com.hilltop.domain.response.BookingResponseDto;
import com.hilltop.enums.SuccessResponseStatusType;
import com.hilltop.exception.BookingServiceException;
import com.hilltop.model.Booking;
import com.hilltop.service.BookingService;
import com.hilltop.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Booking Controller
 */
@RestController
@RequestMapping("/api/v1/booking")
@Slf4j
public class BookingController extends Controller {
    private final BookingService bookingService;

    public BookingController(Translator translator, BookingService bookingService) {
        super(translator);
        this.bookingService = bookingService;
    }


    /**
     * This method used to save a booking details.
     *
     * @param bookingRequestDto bookingRequestDto
     * @return bookingResponseDto
     */
    @PostMapping
    public ResponseEntity<ResponseWrapper> saveBooking(@RequestBody BookingRequestDto bookingRequestDto) {

        try {
            Booking booking = new Booking(bookingRequestDto);
            bookingService.saveBooking(booking);
            var bookingResponseDto = new BookingResponseDto(booking);
            log.info("Successfully save a booking detail for id: {}.", booking.getId());
            return getSuccessResponse(bookingResponseDto, SuccessResponseStatusType.CREATE_BOOKING, HttpStatus.CREATED);
        } catch (BookingServiceException e) {
            log.error("Saving booking was failed.", e);
            return getInternalServerError();
        }
    }
}
