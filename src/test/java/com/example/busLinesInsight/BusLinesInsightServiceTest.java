package com.example.busLinesInsight;

import com.example.busLinesInsight.infrastructure.BusLines;
import com.example.busLinesInsight.infrastructure.BusStops;
import com.example.busLinesInsight.infrastructure.TrafiklabApiClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BusLinesInsightServiceTest {

    @Mock
    private TrafiklabApiClient trafiklabApiClient;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGroupBusLinesByNumber() {
        List<BusLines> busLinesWithStops = new ArrayList<>();
        busLinesWithStops.add(new BusLines("10", "1", "123", "", ""));
        busLinesWithStops.add(new BusLines("10", "1", "123", "", ""));
        busLinesWithStops.add(new BusLines("20", "2", "456", "", ""));

        BusLinesInsightService busLinesInsightService = new BusLinesInsightService(trafiklabApiClient);
        Map<String, List<BusLines>> groupedResults = busLinesInsightService.groupBusLinesByNumber(busLinesWithStops);

        assertEquals(2, groupedResults.size());
        assertEquals(2, groupedResults.get("10").size());
        assertEquals(1, groupedResults.get("20").size());
    }

    @Test
    public void testGetBusStopCounts() {
        List<BusLines> busLinesWithStops = new ArrayList<>();
        busLinesWithStops.add(new BusLines("10", "1", "123", "", ""));
        busLinesWithStops.add(new BusLines("10", "2", "123", "", ""));
        busLinesWithStops.add(new BusLines("20", "1", "456", "", ""));
        busLinesWithStops.add(new BusLines("20", "2", "457", "", ""));

        BusLinesInsightService busLinesInsightService = new BusLinesInsightService(trafiklabApiClient);
        Map<String, List<BusLines>> groupedResults = busLinesInsightService.groupBusLinesByNumber(busLinesWithStops);

        Map<String, Integer> busStopCounts = busLinesInsightService.getBusStopCounts(groupedResults);

        assertEquals(1, busStopCounts.get("10"));
        assertEquals(2, busStopCounts.get("20"));
    }

    @Test
    public void testGetSortedBusNumbers() {
        Map<String, Integer> busStopCounts = new HashMap<>();
        busStopCounts.put("10", 8);
        busStopCounts.put("20", 12);
        busStopCounts.put("30", 10);

        BusLinesInsightService busLinesInsightService = new BusLinesInsightService(trafiklabApiClient);
        List<String> sortedBusNumbers = busLinesInsightService.getTop10SortedBusNumbers(busStopCounts);

        assertEquals(3, sortedBusNumbers.size());
        assertEquals("20", sortedBusNumbers.get(0));
        assertEquals("30", sortedBusNumbers.get(1));
        assertEquals("10", sortedBusNumbers.get(2));
    }



    @Test
    public void testGetJourneyPatternPointNumbers() {
        List<BusLines> busLine = new ArrayList<>();
        busLine.add(new BusLines("10", "1", "123", "", ""));
        busLine.add(new BusLines("10", "1", "124", "", ""));
        busLine.add(new BusLines("10", "2", "102", "", ""));

        BusLinesInsightService busLinesInsightService = new BusLinesInsightService(trafiklabApiClient);
        List<String> journeyPatternPointNumbers = busLinesInsightService.getJourneyPatternPointNumbers(busLine);

        assertEquals(3, journeyPatternPointNumbers.size());
        assertTrue(journeyPatternPointNumbers.contains("123"));
        assertTrue(journeyPatternPointNumbers.contains("124"));
        assertTrue(journeyPatternPointNumbers.contains("102"));
    }

    @Test
    public void testGetBusStopNames() {
        List<BusStops> busStopDetails = new ArrayList<>();
        busStopDetails.add(new BusStops("123", "Stop 1", ""));
        busStopDetails.add(new BusStops("124", "Stop 2", ""));
        busStopDetails.add(new BusStops("125", "Stop 3", ""));

        List<String> journeyPatternPointNumbers = new ArrayList<>();
        journeyPatternPointNumbers.add("123");
        journeyPatternPointNumbers.add("125");

        BusLinesInsightService busLinesInsightService = new BusLinesInsightService(trafiklabApiClient);
        List<BusStops> busStopNames = busLinesInsightService.getBusStopNames(busStopDetails, journeyPatternPointNumbers);

        assertEquals(2, busStopNames.size());
    }
}