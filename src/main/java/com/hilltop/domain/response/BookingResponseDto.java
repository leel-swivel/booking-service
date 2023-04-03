package com.hilltop.domain.response;

import com.hilltop.model.Booking;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class BookingResponseDto extends ResponseDto {
    private final String id;
    private final Date checkInDate;
    private final Date checkOutDate;
    private final BigDecimal fullAmount;
    private final BigDecimal dueAmount;

    public BookingResponseDto(Booking booking) {
        this.id = booking.getId();
        this.checkInDate = booking.getCheckInDate();
        this.checkOutDate = booking.getCheckOutDate();
        this.fullAmount = booking.getFullAmount();
        this.dueAmount = booking.getDueAmount();
    }
}
