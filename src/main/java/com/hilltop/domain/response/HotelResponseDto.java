package com.hilltop.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * HotelResponseDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseDto extends ResponseDto {
    private String id;
    private String name;
    private String description;
    private String city;
    private String telephone;
    private String email;
    private List<String> imageUrl;

}
