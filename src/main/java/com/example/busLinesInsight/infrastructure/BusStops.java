package com.example.busLinesInsight.infrastructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStops {

    @JsonProperty("StopPointNumber")
    private String stopPointNumber;

    @JsonProperty("StopPointName")
    private String stopPointName;

    @JsonProperty("StopAreaNumber")
    private String stopAreaNumber;

    public BusStops() {
        this.stopPointNumber = "";
        this.stopPointName = "";
        this.stopAreaNumber = "";
    }

    public BusStops(String stopPointNumber, String stopPointName, String stopAreaNumber) {
        this.stopPointNumber = stopPointNumber;
        this.stopPointName = stopPointName;
        this.stopAreaNumber = stopAreaNumber;
    }

    public String getStopPointNumber() {
        return stopPointNumber;
    }

    public void setStopPointNumber(String stopPointNumber) {
        if (stopPointNumber == null || stopPointNumber.isEmpty()) {
            throw new IllegalArgumentException("StopPointNumber cannot be null or empty");
        }
        this.stopPointNumber = stopPointNumber;
    }

    public String getStopPointName() {
        return stopPointName;
    }

    public void setStopPointName(String stopPointName) {
        this.stopPointName = stopPointName;
    }

    public String getStopAreaNumber() {
        return stopAreaNumber;
    }

    public void setStopAreaNumber(String stopAreaNumber) {
        if (stopAreaNumber == null || stopAreaNumber.isEmpty()) {
            throw new IllegalArgumentException("StopAreaNumber cannot be null or empty");
        }
        this.stopAreaNumber = stopAreaNumber;
    }

    @Override
    public String toString() {
        return "BusStops{" +
                "stopPointNumber='" + stopPointNumber + '\'' +
                ", stopPointName='" + stopPointName + '\'' +
                ", stopAreaNumber='" + stopAreaNumber + '\'' +
                '}';
    }
}
