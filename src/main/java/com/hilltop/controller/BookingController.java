package com.hilltop.controller;

import com.hilltop.configuration.Translator;
import com.hilltop.enums.ErrorResponseStatusType;
import com.hilltop.enums.SuccessResponseStatusType;
import com.hilltop.exception.BookingServiceException;
import com.hilltop.exception.InvalidBookingException;
import com.hilltop.service.BookingService;
import com.hilltop.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * This endpoint used to search all room by city and pax count.
     *
     * @param city     city
     * @param paxCount paxCount
     * @param dayCount dayCount
     * @return searchRoomListResponseDto
     */
    @GetMapping("/city/{city}/no-of-pax/{paxCount}/days/{dayCount}")
    public ResponseEntity<ResponseWrapper> search(@PathVariable String city,
                                                  @PathVariable int paxCount,
                                                  @PathVariable int dayCount) {
        try {
            var searchRoomListResponseDto = bookingService.searchRooms(city, paxCount, dayCount);
            return getSuccessResponse(searchRoomListResponseDto, SuccessResponseStatusType.SEARCH_ROOM);
        } catch (InvalidBookingException e) {
            log.error("No hotels found in the area of city :{} ", city);
            return getErrorResponse(ErrorResponseStatusType.NO_HOTEL_FOUND);
        } catch (BookingServiceException e) {
            log.error("Getting result for room search was failed.", e);
            return getInternalServerError();
        }
    }
}
