package com.example.busLinesInsight.infrastructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStopResponseEntity {

    @JsonProperty("StatusCode")
    private int statusCode;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("ExecutionTime")
    private int executionTime;

    @JsonProperty("ResponseData")
    private ResponseData responseData;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {

        @JsonProperty("Version")
        private String version;

        @JsonProperty("Type")
        private String type;

        @JsonProperty("Result")
        private List<BusStops> busStops;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<BusStops> getResult() {
            return busStops;
        }

        public void setResult(List<BusStops> busLines) {
            this.busStops = busLines;
        }
    }


}
