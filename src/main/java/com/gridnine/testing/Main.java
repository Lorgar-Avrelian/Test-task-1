package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Flights;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.impl.FlightServiceImpl;
import com.gridnine.testing.test_factory.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Obtaining test samples
        System.out.println(FlightBuilder.createFlights());
        System.out.println("========================================================================================");
        // Task solution
        Flights flights = new Flights(FlightBuilder.createFlights());
        FlightService flightService = new FlightServiceImpl();
        // Receiving a flight departing in the past
        List<Flight> flightList = flightService.beforeNow(flights);
        System.out.println(flightList);
        // Receiving a flight that departs before it arrives
        flightList = flightService.arrivalDateBeforeDepartureDate(flights);
        System.out.println(flightList);
        // Receiving a flight with more than two hours ground time
        flightList = flightService.parkingMoreThen2Hours(flights);
        System.out.println(flightList);
    }
}