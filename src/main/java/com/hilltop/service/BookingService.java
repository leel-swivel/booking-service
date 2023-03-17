package com.hilltop.service;

import com.hilltop.domain.request.HotelIdRequestDto;
import com.hilltop.domain.response.HotelListResponseDto;
import com.hilltop.domain.response.HotelResponseDto;
import com.hilltop.domain.response.SearchRoomListResponseDto;
import com.hilltop.exception.InvalidBookingException;
import com.hilltop.wrapper.HotelListResponseWrapper;
import com.hilltop.wrapper.RoomListResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * BookingService
 */
@Service
@Slf4j
public class BookingService {

    private static final String HOTEL_CITY_REPLACE_PHASE = "##CITY##";
    private static final String PAX_COUNT_REPLACE_PHASE = "##PAX_COUNT##";
    private static final String DAYS_COUNT_REPLACE_PHASE = "##NO_OF_DAYS##";
    private final RestTemplate restTemplate;
    private final String getHotelListByCityUrl;
    private final String getRoomList;

    public BookingService(@Value("${hotel.getHotelsByCityUrl}") String getHotelList,
                          @Value("${room.getRoomsByPaxCountAndHotelIds}") String getRoomList,
                          RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.getHotelListByCityUrl = getHotelList;
        this.getRoomList = getRoomList;
    }

    /**
     * This method used to get room by search.
     *
     * @param city       city
     * @param paxCount   paxCount
     * @param noOfNights noOfNights
     * @return
     */
    public SearchRoomListResponseDto searchRooms(String city, int paxCount, int noOfNights) {
        var hotelIdList = getHotelIdList(city);
        if (!hotelIdList.isEmpty()) {
            return getRoomsByPaxCountAndHotelIds(hotelIdList, paxCount, noOfNights);
        } else {
            throw new InvalidBookingException("No hotel found in " + city);
        }

    }

    /**
     * This method used to get hotel id list by city.
     *
     * @param city city
     * @return List of String
     */
    public List<String> getHotelIdList(String city) {
        var url = getHotelListByCityUrl.replace(HOTEL_CITY_REPLACE_PHASE, city);

        ResponseEntity<HotelListResponseWrapper> result = restTemplate.exchange(url, HttpMethod.GET, null, HotelListResponseWrapper.class);
        String responseBody = Objects.requireNonNull(result.getBody()).getData().toLogJson();
        log.debug("Getting hotel list by city was successful. statusCode: {}, response: {}",
                result.getStatusCode(), responseBody);
        HotelListResponseDto hotelList = Objects.requireNonNull(result.getBody()).getData();
        return hotelList.getHotelList()
                .stream()
                .map(HotelResponseDto::getId)
                .collect(Collectors.toList());
    }

    /**
     * This method used to get rooms by pax count and hotel ids.
     *
     * @param hotelIds hotelIds
     * @param paxCount paxCount
     * @param noOfDays noOfDays
     * @return SearchRoomListResponseDto
     */
    private SearchRoomListResponseDto getRoomsByPaxCountAndHotelIds(List<String> hotelIds, int paxCount, int noOfDays) {
        var url = getRoomList.replace(PAX_COUNT_REPLACE_PHASE, String.valueOf(paxCount))
                .replace(DAYS_COUNT_REPLACE_PHASE, String.valueOf(noOfDays));
        HotelIdRequestDto hotelIdRequestDto = new HotelIdRequestDto(hotelIds);
        HttpEntity<HotelIdRequestDto> entity = new HttpEntity<>(hotelIdRequestDto);
        var result =
                restTemplate.exchange(url, HttpMethod.POST, entity, RoomListResponseWrapper.class);
        return Objects.requireNonNull(result.getBody()).getData();

    }
}
