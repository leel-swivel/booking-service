package com.hilltop.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRoomListResponseDto extends ResponseDto {

    private List<SearchRoomResponseDto> searchRoomList;


}
