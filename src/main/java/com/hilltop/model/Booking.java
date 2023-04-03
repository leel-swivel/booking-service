package com.hilltop.model;

import com.hilltop.domain.request.BookingRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Booking Entity
 */
@Entity
@Table(name = "booking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Transient
    private static final String BOOKING_ID_PREFIX = "bid-";
    @Id
    private String id;
    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal fullAmount;
    private BigDecimal dueAmount;


    public Booking(BookingRequestDto bookingRequestDto) {
        this.id = BOOKING_ID_PREFIX + UUID.randomUUID();
        this.checkInDate = bookingRequestDto.getCheckInDate();
        this.checkOutDate = bookingRequestDto.getCheckOutDate();
        this.fullAmount = bookingRequestDto.getFullAmount();
        this.dueAmount = bookingRequestDto.getDueAmount();
    }
}
