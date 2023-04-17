package com.example.busLinesInsight;

import com.example.busLinesInsight.infrastructure.BusLines;
import com.example.busLinesInsight.infrastructure.BusStops;
import com.example.busLinesInsight.infrastructure.TrafiklabApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BusLinesInsightService {

    private final TrafiklabApiClient trafiklabApiClient;

    private final Logger logger = LoggerFactory.getLogger(BusLinesInsightService.class);

    @Autowired
    public BusLinesInsightService(TrafiklabApiClient trafiklabApiClient) {
        this.trafiklabApiClient = trafiklabApiClient;
    }

    public void findBusLinesWithMostStops() {

        List<BusLines> busLinesWithStops = trafiklabApiClient.getBusLinesWithStops();
        List<BusStops> busStopDetails = trafiklabApiClient.getBusStopDetails();

        Map<String, List<BusLines>> groupedResults = groupBusLinesByNumber(busLinesWithStops);

        Map<String, Integer> busStopCounts = getBusStopCounts(groupedResults);

        List<String> top10SortedBusNumbers = getTop10SortedBusNumbers(busStopCounts);

        printTop10BusLines(busStopCounts, top10SortedBusNumbers);

        // Get the bus line with the stops
        List<BusLines> busLineWithMaxStops = groupedResults.get(top10SortedBusNumbers.get(00));

        List<String> journeyPatternPointNumbers = getJourneyPatternPointNumbers(busLineWithMaxStops);

        List<BusStops> busStopNames = getBusStopNames(busStopDetails, journeyPatternPointNumbers);

        printBusStopNames(busLineWithMaxStops, busStopNames);
    }

    // Print the top 10 bus numbers with the most stops
    private void printTop10BusLines(Map<String, Integer> busStopCounts, List<String> sortedBusNumbers) {
        System.out.println("***Top 10 bus lines with the most stops:");
        sortedBusNumbers.forEach(busNumber -> System.out.println("Bus Line number: " + busNumber + ", Bus stop count: "
                + busStopCounts.get(busNumber)));
    }

    // Print the stop names and stop point numbers for the bus stops
    private void printBusStopNames(List<BusLines> busLine, List<BusStops> busStopNames) {
        System.out.println("***Stop names and stop point numbers for bus line " + busLine.get(0).getLineNumber() + ":");
        busStopNames.forEach(busStop ->
                System.out.println("Stop name: " + busStop.getStopPointName() + ", Stop point number: " + busStop.getStopPointNumber())
        );
    }

    // Get the bus stops details for the bus stop (journey pattern point)
    List<BusStops> getBusStopNames(List<BusStops> busStopDetails, List<String> journeyPatternPointNumbers) {
        return busStopDetails.stream()
                .filter(busStop -> journeyPatternPointNumbers.contains(busStop.getStopPointNumber()))
                .toList();
    }

    // Get list of bus tops((journey pattern points ) for the bus line
    List<String> getJourneyPatternPointNumbers(List<BusLines> busLine) {
        return busLine.stream()
                .map(BusLines::getJourneyPatternPointNumber)
                .toList();
    }

    // Sort the bus numbers by their stop count in descending order and return top 10
    List<String> getTop10SortedBusNumbers(Map<String, Integer> busStopCounts) {
        return busStopCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(10)
                .toList();
    }

    // Map each bus number to the count of unique bus stops on its route
    Map<String, Integer> getBusStopCounts(Map<String, List<BusLines>> groupedResults) {
        return groupedResults.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .map(BusLines::getJourneyPatternPointNumber)
                                .collect(Collectors.toSet())
                                .size()
                ));
    }

    // Group the results by bus number
    Map<String, List<BusLines>> groupBusLinesByNumber(List<BusLines> busLinesWithStops) {
        return busLinesWithStops.stream()
                .collect(Collectors.groupingBy(BusLines::getLineNumber));
    }
}
