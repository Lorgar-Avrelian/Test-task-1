package com.gridnine.testing;

import com.gridnine.testing.flight_builder.FlightBuilder;

public class Main {
    public static void main(String[] args) {
        FlightBuilder flightBuilder = new FlightBuilder();
        System.out.println(flightBuilder.createFlights());
    }
}