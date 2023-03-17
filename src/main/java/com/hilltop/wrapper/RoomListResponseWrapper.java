package com.hilltop.wrapper;

import com.hilltop.domain.BaseDto;
import com.hilltop.domain.response.SearchRoomListResponseDto;
import com.hilltop.enums.ResponseStatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomListResponseWrapper implements BaseDto {
    private ResponseStatusType status;
    private String message;
    private SearchRoomListResponseDto data;
    private String displayMessage;

    @Override
    public String toLogJson() {
        return toJson();
    }
}
