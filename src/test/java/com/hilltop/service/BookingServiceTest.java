package com.hilltop.service;

import com.hilltop.domain.response.HotelListResponseDto;
import com.hilltop.domain.response.HotelResponseDto;
import com.hilltop.domain.response.SearchRoomListResponseDto;
import com.hilltop.wrapper.HotelListResponseWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BookingServiceTest {


    private BookingService bookingService;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        String getHotelsByCityUrl = "http://localhost:8083/api/v1/hotel/city/##CITY##";
        String getRoomsByPaxCountAndHotelIds = "http://localhost:8084/api/v1/room/hotel/pax-count/##PAX_COUNT##/no-of-days/##NO_OF_DAYS##";
    }


    @AfterEach
    void tearDown() {
    }


//    @Test
    void Should_Return_HotelIdList_When_CityProvided() {
        List<String> sampleHotelIds = getSampleHotelIds();

        HotelListResponseWrapper sampleHotelListResponseDto = getSampleHotelListResponseDto();
        when(bookingService.getHotelIdList(anyString())).thenReturn(any());
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(HotelListResponseWrapper.class))).thenReturn(any());
        assertEquals(sampleHotelIds.get(0),bookingService.getHotelIdList(anyString()).get(0));


    }


    private List<String> getSampleHotelIds() {
        List<String> hotelIds = new ArrayList<>();
        hotelIds.add("hid-1enjag-bnmdbjn");
        hotelIds.add("hid-2enjag-bnmdbjn");
        return hotelIds;
    }


    private HotelListResponseWrapper getSampleHotelListResponseDto() {

        HotelResponseDto hotelResponseDto1 = new HotelResponseDto("hid-1enjag-bnmdbjn", "sample", "sample", "sample", "sample", "sample", new ArrayList<>());
        HotelResponseDto hotelResponseDto2 = new HotelResponseDto("hid-2enjag-bnmdbjn", "sample", "sample", "sample", "sample", "sample", new ArrayList<>());
        List<HotelResponseDto> hotelResponseDtoList = new ArrayList<>();
        hotelResponseDtoList.add(hotelResponseDto1);
        hotelResponseDtoList.add(hotelResponseDto2);
        HotelListResponseDto hotelListResponseDto = new HotelListResponseDto();
        hotelListResponseDto.setHotelList(hotelResponseDtoList);
//        return hotelResponseDtoList;

        HotelListResponseWrapper hotelListResponseWrapper = new HotelListResponseWrapper();
        hotelListResponseWrapper.setData(hotelListResponseDto);
        return hotelListResponseWrapper;

    }
}