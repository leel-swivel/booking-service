package com.hilltop.wrapper;

import com.hilltop.domain.BaseDto;
import com.hilltop.domain.response.HotelListResponseDto;
import com.hilltop.enums.ResponseStatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HotelListResponseWrapper implements BaseDto {

    private ResponseStatusType status;
    private String message;
    private HotelListResponseDto data;
    private String displayMessage;

    @Override
    public String toLogJson() {
        return toJson();
    }
}
