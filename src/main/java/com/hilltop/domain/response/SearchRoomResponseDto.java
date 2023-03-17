package com.hilltop.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class SearchRoomResponseDto extends ResponseDto {

    private String id;
    private int roomNumber;
    private String hotelId;
    private String roomTypeName;
    private BigDecimal pricePerNight;
    private int paxCount;
    private List<String> imageUrls;
    private BigDecimal totalCost;

}
