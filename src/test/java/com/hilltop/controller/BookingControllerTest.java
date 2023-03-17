package com.hilltop.controller;

import com.hilltop.configuration.Translator;
import com.hilltop.domain.response.SearchRoomListResponseDto;
import com.hilltop.domain.response.SearchRoomResponseDto;
import com.hilltop.service.BookingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookingControllerTest {

    private static final String SEARCH_ROOM_URL = "/api/v1/booking/city/{city}/no-of-pax/{paxCount}/days/{dayCount}";
    private static final String CITY = "Kalutara";
    private static final String PAX_COUNT = "2";
    private static final String DAY_COUNT = "2";
    @Mock
    private BookingService bookingService;
    @Mock
    private Translator translator;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        initMocks(this);
        BookingController bookingController = new BookingController(translator, bookingService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @AfterEach
    void tearDown() {
    }

//    @Test
    void Should_ReturnOk_WhenSearchRoom() throws Exception {
        String url = SEARCH_ROOM_URL.replace("{city}", CITY).replace("{paxCount}", PAX_COUNT).replace("{dayCount}", DAY_COUNT);
        SearchRoomListResponseDto searchRoomListResponseDto = getSampleSearchRoomListResponseDto();

        when(bookingService.searchRooms(CITY, Integer.valueOf(PAX_COUNT), Integer.valueOf(DAY_COUNT))).thenReturn(searchRoomListResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk());

    }


    private SearchRoomListResponseDto getSampleSearchRoomListResponseDto() {
        SearchRoomListResponseDto searchRoomListResponseDto = new SearchRoomListResponseDto();
        SearchRoomResponseDto searchRoomResponseDto = new SearchRoomResponseDto();
        searchRoomResponseDto.setId("rid-gegaj");
        searchRoomResponseDto.setRoomNumber(4);
        searchRoomResponseDto.setRoomTypeName("Single-ROOM");
        searchRoomResponseDto.setPaxCount(2);
        searchRoomResponseDto.setPricePerNight(BigDecimal.valueOf(5000.00));
        searchRoomResponseDto.setTotalCost(BigDecimal.valueOf(10000.00));

        searchRoomListResponseDto.getSearchRoomList().add(searchRoomResponseDto);

        return searchRoomListResponseDto;
    }
}