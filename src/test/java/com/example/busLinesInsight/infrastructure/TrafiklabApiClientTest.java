package com.example.busLinesInsight.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class TrafiklabApiClientTest {

    private static final String API_KEY = "test-api-key";
    private static final String API_URL = "https://api.sl.se/api2/LineData.json";
    private static final String TRANSPORT_MODE = "BUS";

    @Mock
    private RestTemplate restTemplate;

    private TrafiklabApiClient trafiklabApiClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trafiklabApiClient = new TrafiklabApiClient(restTemplate);
    }

    @Test
    void testGetBusLinesWithStopsReturnsEmptyListOnErrorResponse() {
        when(restTemplate.exchange(any(RequestEntity.class), eq(BusLineResponseEntity.class))).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        List<BusLines> busLines = trafiklabApiClient.getBusLinesWithStops();

        assertEquals(Collections.emptyList(), busLines);
    }

    @Test
    void testGetBusStopDetailsThrowsExceptionOnErrorResponse() {
        when(restTemplate.exchange(any(RequestEntity.class), eq(BusStopResponseEntity.class))).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        List<BusStops> busStopDetails = trafiklabApiClient.getBusStopDetails();

        assertEquals(Collections.emptyList(), busStopDetails);
    }
}
