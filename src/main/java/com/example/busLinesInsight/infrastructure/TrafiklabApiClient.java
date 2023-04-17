package com.example.busLinesInsight.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Component
public class TrafiklabApiClient {

    private static final String API_KEY = "0172da23bc1a49a98c085e176909c98d";
    private static final String API_URL = "https://api.sl.se/api2/LineData.json";
    private static final String TRANSPORT_MODE = "BUS";
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TrafiklabApiClient.class);


    @Autowired
    public TrafiklabApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BusLines> getBusLinesWithStops() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Get Bus lines information
        URI uri_model_jour = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("model", "jour")
                .queryParam("DefaultTransportModeCode", TRANSPORT_MODE)
                .queryParam("key", API_KEY)
                .build()
                .toUri();
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, uri_model_jour);
        ResponseEntity<BusLineResponseEntity> lineResponse = null;
        try {
            lineResponse = restTemplate.exchange(request, BusLineResponseEntity.class);
        } catch (RestClientException e) {
            logger.error("Error getting bus line info from TrafiklabsApi: {}", e.getMessage());
            return Collections.emptyList();
        }

        BusLineResponseEntity lineResponseBody = lineResponse.getBody();
        if (lineResponseBody == null || lineResponseBody.getResponseData() == null || lineResponseBody.getResponseData().getResult() == null) {
            logger.warn("Null response or missing data while retrieving bus line info from TrafiklabApi");
            return Collections.emptyList();
        } else {
            // Extract the list of Result objects from the bus line response
            return lineResponseBody.getResponseData().getResult();
        }
    }

    public List<BusStops> getBusStopDetails() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Get Bus stops information
        URI uri_model_stop = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("model", "stop")
                .queryParam("DefaultTransportModeCode", TRANSPORT_MODE)
                .queryParam("key", API_KEY)
                .build()
                .toUri();
        RequestEntity<Void> modelStopRequest = new RequestEntity<>(headers, HttpMethod.GET, uri_model_stop);
        ResponseEntity<BusStopResponseEntity> busStopResponse = null;
        try {
            busStopResponse = restTemplate.exchange(modelStopRequest, BusStopResponseEntity.class);
        } catch (RestClientException e) {
            logger.error("Error getting bus stop details from TrafiklabApi {}", e.getMessage());
            return Collections.emptyList();
        }

        BusStopResponseEntity busStopResponseBody = busStopResponse.getBody();
        if (busStopResponseBody == null || busStopResponseBody.getResponseData() == null || busStopResponseBody.getResponseData().getResult() == null) {
            logger.warn("Null response or missing data while retrieving bus line info from TrafiklabApi");
            return Collections.emptyList();
        } else {
            // Extract the list of Result objects from the bus stop response
            return busStopResponseBody.getResponseData().getResult();
        }
    }

}
