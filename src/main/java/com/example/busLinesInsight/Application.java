package com.example.busLinesInsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    private final BusLinesInsightService busLinesInsightService;

    public Application(BusLinesInsightService busLinesInsightService) {
        this.busLinesInsightService = busLinesInsightService;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Application app = context.getBean(Application.class);
        app.run();
    }

    public void run() {
        busLinesInsightService.findBusLinesWithMostStops();
    }
}
