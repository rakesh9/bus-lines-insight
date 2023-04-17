package com.example.busLinesInsight.infrastructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusLines {

    @JsonProperty("LineNumber")
    private String lineNumber;

    @JsonProperty("DirectionCode")
    private String directionCode;

    @JsonProperty("JourneyPatternPointNumber")
    private String journeyPatternPointNumber;

    @JsonProperty("LastModifiedUtcDateTime")
    private String lastModifiedUtcDateTime;

    @JsonProperty("ExistsFromDate")
    private String existsFromDate;

    public BusLines() {
    }

    public BusLines(String lineNumber, String directionCode, String journeyPatternPointNumber,
                    String lastModifiedUtcDateTime, String existsFromDate) {
        this.lineNumber = lineNumber;
        this.directionCode = directionCode;
        this.journeyPatternPointNumber = journeyPatternPointNumber;
        this.lastModifiedUtcDateTime = lastModifiedUtcDateTime;
        this.existsFromDate = existsFromDate;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDirectionCode() {
        return directionCode;
    }

    public void setDirectionCode(String directionCode) {
        this.directionCode = directionCode;
    }

    public String getJourneyPatternPointNumber() {
        return journeyPatternPointNumber;
    }

    public void setJourneyPatternPointNumber(String journeyPatternPointNumber) {
        this.journeyPatternPointNumber = journeyPatternPointNumber;
    }

    public String getLastModifiedUtcDateTime() {
        return lastModifiedUtcDateTime;
    }

    public void setLastModifiedUtcDateTime(String lastModifiedUtcDateTime) {
        this.lastModifiedUtcDateTime = lastModifiedUtcDateTime;
    }

    public String getExistsFromDate() {
        return existsFromDate;
    }

    public void setExistsFromDate(String existsFromDate) {
        this.existsFromDate = existsFromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusLines busLines = (BusLines) o;
        return Objects.equals(lineNumber, busLines.lineNumber) &&
                Objects.equals(directionCode, busLines.directionCode) &&
                Objects.equals(journeyPatternPointNumber, busLines.journeyPatternPointNumber) &&
                Objects.equals(lastModifiedUtcDateTime, busLines.lastModifiedUtcDateTime) &&
                Objects.equals(existsFromDate, busLines.existsFromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, directionCode, journeyPatternPointNumber,
                lastModifiedUtcDateTime, existsFromDate);
    }

    @Override
    public String toString() {
        return "BusLines{" +
                "lineNumber='" + lineNumber + '\'' +
                ", directionCode='" + directionCode + '\'' +
                ", journeyPatternPointNumber='" + journeyPatternPointNumber + '\'' +
                ", lastModifiedUtcDateTime='" + lastModifiedUtcDateTime + '\'' +
                ", existsFromDate='" + existsFromDate + '\'' +
                '}';
    }
}
