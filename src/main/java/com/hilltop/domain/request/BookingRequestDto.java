package com.hilltop.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class BookingRequestDto {

    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal fullAmount;
    private BigDecimal dueAmount;


}
